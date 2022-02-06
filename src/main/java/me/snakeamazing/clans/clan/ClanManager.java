package me.snakeamazing.clans.clan;

import me.snakeamazing.clans.file.YAMLFile;
import me.snakeamazing.clans.file.FileMatcher;
import me.snakeamazing.clans.rank.ClanRank;
import me.snakeamazing.clans.service.ClanService;
import me.snakeamazing.clans.service.UserService;
import me.snakeamazing.clans.user.ClanUser;
import me.snakeamazing.clans.user.DefaultClanUser;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

public class ClanManager {

    private final YAMLFile config;

    private final ClanService clanService;
    private final UserService userService;

    public ClanManager(ClanService clanService, UserService userService, FileMatcher matcher) {
        this.config = matcher.getFile("config");

        this.clanService = clanService;
        this.userService = userService;
    }

    public void createClan(String name, String prefix, Player leader) {
        if (userService.checkIfPlayerExists(leader.getName())) {
            leader.sendMessage("You already have a clan");
            return;
        }

        if (clanService.checkIfClanExists(name)) {
            leader.sendMessage("Clan already exists.");
            return;
        }

        Clan clan = new DefaultClan(name, prefix, leader.getUniqueId());
        clanService.save(clan, true);

        ClanUser clanUser = new DefaultClanUser(leader.getName(), leader.getUniqueId().toString(),
                clan.getName(), ClanRank.LEADER);

        userService.save(clanUser, true);

        leader.sendMessage("Clan created correctly");
    }

    public void disbandClan(Player player) {
        Clan clan = getClanByPlayer(player.getName());

        if (clan == null) {
            return;
        }

        if (clan.getLeader() != player.getUniqueId()) {
            return;
        }

        for (UUID uuid : clan.getAllMembers()) {
            userService.removeFromDatabase(uuid);
        }
    }

    public void addPlayerToClan(Player player, String clanName) {
        if (userService.checkIfPlayerExists(player.getName())) {
            return;
        }

        clanService
                .find(clanName)
                .ifPresent(clan -> {
                    clan.addMember(player.getUniqueId());
                    ClanUser clanUser = new DefaultClanUser(player.getName(), player.getUniqueId().toString(),
                            clanName, ClanRank.USER);

                    userService.save(clanUser, true);
                });
    }

    public Clan getClanByPlayer(String playerName) {
        Optional<Clan> optionalClan = clanService.findByPlayer(playerName);

        return optionalClan.orElse(null);
    }


}
