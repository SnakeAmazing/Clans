package me.snakeamazing.clans.clan;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class ClanHandler {

    private final Map<String, String> pendingUsers = new HashMap<>();

    private final ClanManager clanManager;

    public ClanHandler(ClanManager clanManager) {
        this.clanManager = clanManager;
    }

    public void invitePlayerToClan(Player player, String clanName) {
        pendingUsers.put(player.getName(), clanName);
    }

    public void addPlayerToClan(Player player) {
        clanManager.addPlayerToClan(player, pendingUsers.remove(player.getName()));
    }

    public boolean isPlayerPending(Player player) {
        return pendingUsers.containsKey(player.getName());
    }

    public void removePendingPlayer(Player player) {
        pendingUsers.remove(player.getName());
    }
}
