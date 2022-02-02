package me.snakeamazing.clans.hook.placeholderapi;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.snakeamazing.clans.clan.Clan;
import me.snakeamazing.clans.clan.ClanManager;
import me.snakeamazing.clans.file.FileMatcher;
import me.snakeamazing.clans.file.YAMLFile;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class ClansExpansion extends PlaceholderExpansion {

    private final ClanManager clanManager;
    private final YAMLFile config;

    public ClansExpansion(ClanManager clanManager, FileMatcher matcher) {
        this.clanManager = clanManager;

        this.config = matcher.getFile("config");
    }

    @Override
    public @NotNull String getIdentifier() {
        return "clans";
    }

    @Override
    public @NotNull String getAuthor() {
        return "SnakeAmazing";
    }

    @Override
    public @NotNull String getVersion() {
        return "0.0.1";
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if(params.equalsIgnoreCase("prefix")) {
            Clan clan = clanManager.getClanByPlayer(player.getName());

            if (clan != null) {
                return config.getString("prefix-format")
                        .replace("%color%", String.valueOf(clan.getColor()))
                        .replace("%prefix%", clan.getPrefix());
            }

            return config.getString("chat.no-clan-prefix");
        }

        return null;
    }
}
