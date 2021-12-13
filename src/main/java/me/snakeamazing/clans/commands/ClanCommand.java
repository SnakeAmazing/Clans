package me.snakeamazing.clans.commands;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.snakeamazing.clans.clan.ClanManager;
import org.bukkit.entity.Player;

@Command(names = "clan", permission = "clans.clan")
public class ClanCommand implements CommandClass {

    private final ClanManager clanManager;

    public ClanCommand(ClanManager clanManager) {
        this.clanManager = clanManager;
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


}
