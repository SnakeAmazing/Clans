package me.snakeamazing.clans.commands;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.snakeamazing.clans.clan.ClanHandler;
import me.snakeamazing.clans.clan.ClanManager;
import org.bukkit.entity.Player;

@Command(names = "clan", permission = "clans.clan")
public class ClanCommand implements CommandClass {

    private final ClanManager clanManager;
    private final ClanHandler clanHandler;

    public ClanCommand(ClanManager clanManager, ClanHandler clanHandler) {
        this.clanManager = clanManager;
        this.clanHandler = clanHandler;
    }

    @Command(names = "")
    public boolean onClanCommand(@Sender Player player) {
        player.sendMessage("Help");
        return false;
    }

    @Command(names = "create")
    public boolean createClanCommand(@Sender Player player, String name, String prefix) {
        clanManager.createClan(name, prefix, player);

        return false;
    }

    @Command(names = "invite")
    public void onInviteCommand(@Sender Player player, Player target) {
        clanHandler.invitePlayerToClan(player, target);
    }

    @Command(names = {"refuse", "deny"})
    public void onRefuseCommand(@Sender Player player) {
        clanHandler.removePendingPlayer(player);
    }

    @Command(names = "accept")
    public void onClanAcceptCommand(@Sender Player player) {
        clanHandler.addPlayerToClan(player);
    }
}
