package me.snakeamazing.clans.clan;

import me.snakeamazing.clans.file.FileMatcher;
import me.snakeamazing.clans.file.YAMLFile;
import me.snakeamazing.clans.request.ClanInviteRequest;
import me.snakeamazing.clans.request.Request;
import me.snakeamazing.clans.request.RequestManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ClanHandler {

    private final Map<String, String> pendingUsers = new HashMap<>();

    private final ClanManager clanManager;
    private final RequestManager requestManager;

    private final YAMLFile messages;
    private final YAMLFile config;

    public ClanHandler(ClanManager clanManager, RequestManager requestManager, FileMatcher fileMatcher) {
        this.clanManager = clanManager;
        this.requestManager = requestManager;

        this.messages = fileMatcher.getFile("messages");
        this.config = fileMatcher.getFile("config");
    }

    public void invitePlayerToClan(Player player, Player target) {
        Clan clan = clanManager.getClanByPlayer(player.getName());

        if (clan == null) {
            player.sendMessage(messages.getString("messages.clan.commands.invite.no-clan"));
            return;
        }

        if (clanManager.getClanByPlayer(target.getName()) != null) {
            player.sendMessage(messages.getString("messages.clan.commands.invite.target-has-clan"));
            return;
        }

        pendingUsers.put(target.getUniqueId().toString(), clan.getName());
        player.sendMessage(messages.getString("messages.clan.commands.invite.successful")
                .replace("%player%", target.getName()));

        Request request = new ClanInviteRequest(config.getInt("settings.invite.request-expire"), target);
        requestManager.addRequest(request);
    }

    public void addPlayerToClan(Player player) {

        if (!isPlayerPending(player)) {
            player.sendMessage("messages.clan.commands.invite.no-pending-invite");
            return;
        }

        if (!requestManager.hasPendingRequest(player)) {
            player.sendMessage("messages.clan.commands.invite.no-pending-invite");
            pendingUsers.remove(player.getUniqueId().toString());
            return;
        }

        if (clanManager.getClanByPlayer(player.getName()) != null) return;

        clanManager.addPlayerToClan(player, pendingUsers.remove(player.getName()));
    }

    public boolean isPlayerPending(Player player) {
        return pendingUsers.containsKey(player.getUniqueId().toString());
    }

    public void removePendingPlayer(Player player) {
        player.sendMessage(messages.getString("messages.clan.commands.invite.refuse")
                .replace("%clan%", pendingUsers.remove(player.getUniqueId().toString())));
    }

    public void sendMessageToMembers(Player player, String message) {
        Clan clan = clanManager.getClanByPlayer(player.getName());

        if (clan == null) {
            player.sendMessage(messages.getString("messages.clan.commands.invite.no-clan"));
            return;
        }

        for (UUID uuid : clan.getAllMembers()) {
            Player member = Bukkit.getPlayer(uuid);
            if (member != null) {
                member.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
            }
        }
    }
}
