package me.snakeamazing.clans.service;

import com.mongodb.client.model.Filters;
import me.snakeamazing.clans.cache.Cache;
import me.snakeamazing.clans.clan.Clan;
import me.snakeamazing.clans.storage.MongoStorage;
import me.snakeamazing.clans.user.ClanUser;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

public class UserService {

    private final Cache<String, ClanUser> clanUserCache;
    private final MongoStorage<ClanUser> clanUserStorage;

    public UserService(Cache<String, ClanUser> clanUserCache, MongoStorage<ClanUser> clanUserStorage) {
        this.clanUserCache = clanUserCache;
        this.clanUserStorage = clanUserStorage;
    }

    public ClanUser getInDatabase(String clanName) {
        return clanUserStorage.get(clanName);
    }

    public ClanUser getInDatabaseByName(String playerName) {
        return clanUserStorage.filterAndGetFirst(Filters.eq("name", playerName));
    }

    public void saveInDatabase(ClanUser clanUser) {
        clanUserStorage.save(clanUser);
    }

    public void save(ClanUser clanUser, boolean saveInDatabase) {
        clanUserCache.add(clanUser.getUniqueId(), clanUser);

        if (saveInDatabase) saveInDatabase(clanUser);
    }

    public ClanUser get(String user) {
        if (clanUserCache.exists(user)) {
            return clanUserCache.get(user);
        }

        return getInDatabase(user);
    }

    public ClanUser getByName(String playerName, boolean findInDatabase) {
        Player player = Bukkit.getPlayer(playerName);

        if (player != null) {
            return clanUserCache.get(player.getUniqueId().toString());
        }

        if (findInDatabase) {
            return getInDatabaseByName(playerName);
        }

        return null;
    }

    public void removeFromDatabase(UUID user) {
        if (clanUserCache.exists(user.toString())) {
            clanUserCache.remove(user.toString());
        }

        clanUserStorage.remove(user.toString());
    }

    public boolean checkIfPlayerExists(String user) {
        return clanUserCache.exists(user);
    }

    public Optional<ClanUser> find(UUID user) {
        return Optional.of(get(user.toString()));
    }

    public Optional<ClanUser> findByName(String playerName, boolean findInDatabase) {
        return Optional.of(getByName(playerName, findInDatabase));
    }
}
