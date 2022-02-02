package me.snakeamazing.clans.clan;

import me.snakeamazing.clans.file.YAMLFile;
import me.snakeamazing.clans.file.FileMatcher;
import me.snakeamazing.clans.rank.ClanRank;
import me.snakeamazing.clans.service.ClanService;
import me.snakeamazing.clans.user.ClanUser;
import me.snakeamazing.clans.user.DefaultClanUser;
import org.bukkit.entity.Player;

import java.util.Optional;

public class ClanManager {

    private final YAMLFile config;

    private final ClanService clanService;

    public ClanManager(ClanService clanService, FileMatcher matcher) {
        this.config = matcher.getFile("config");

        this.clanService = clanService;
    }

    public void createClan(String name, String prefix, Player leader) {

        if (clanService.checkIfClanExists(name)) {
            leader.sendMessage("Clan already exists.");
            return;
        }

        Clan clan = new DefaultClan(name, prefix, leader.getName());
        clanService.save(clan, true);

        ClanUser clanUser = new DefaultClanUser(leader.getName(), leader.getUniqueId().toString(),
                clan.getName(), ClanRank.LEADER);
        //TODO USER SERVICE

        leader.sendMessage("Clan created correctly");
    }

    public void addPlayerToClan(Player player, String clanName) {
        clanService
                .find(clanName)
                .ifPresent(clan -> {
                    clan.getMembers().add(player.getName());
                    //TODO USER SERVICE
                    ClanUser clanUser = new DefaultClanUser(player.getName(), player.getUniqueId().toString(),
                            clanName, ClanRank.USER);
                });
    }

    public Clan getClanByPlayer(String playerName) {
        Optional<Clan> optionalClan = clanService.findByPlayer(playerName);

        return optionalClan.orElse(null);
    }


}
