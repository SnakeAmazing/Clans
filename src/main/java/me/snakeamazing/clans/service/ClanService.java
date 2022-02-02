package me.snakeamazing.clans.service;

import com.mongodb.client.model.Filters;
import me.snakeamazing.clans.cache.Cache;
import me.snakeamazing.clans.clan.Clan;
import me.snakeamazing.clans.storage.MongoStorage;
import me.snakeamazing.clans.user.ClanUser;
import org.bukkit.Bukkit;

import java.util.Optional;

public class ClanService {

    private final Cache<String, Clan> clanCache;
    private final MongoStorage<Clan> clanStorage;
    private final Cache<String, ClanUser> clanUserCache;

    public ClanService(Cache<String, Clan> clanCache, MongoStorage<Clan> clanStorage,
                        Cache<String, ClanUser> clanUserCache) {
        this.clanCache = clanCache;
        this.clanStorage = clanStorage;
        this.clanUserCache = clanUserCache;
    }

    public Clan getInDatabase(String clanName) {
        return clanStorage.get(clanName);
    }

    public Clan getInDatabaseByPlayer(String leader) {
        return clanStorage.filterAndGetFirst(Filters.eq("leader", leader));
    }

    public void saveInDatabase(Clan clan) {
        clanStorage.save(clan);
    }

    public void save(Clan clan, boolean saveInDatabase) {
        clanCache.add(clan.getName(), clan);

        if (saveInDatabase) saveInDatabase(clan);
    }

    public Clan get(String clanName) {
        if (clanCache.exists(clanName)) {
            return clanCache.get(clanName);
        }

        return getInDatabase(clanName);
    }

    public Clan getByPlayer(String playerName) {
        if (Bukkit.getPlayer(playerName) == null) {
            return getInDatabase(clanUserCache.get(playerName).getClanName());
        } else {
            return clanCache.get(clanUserCache.get(playerName).getClanName());
        }
    }

    public boolean checkIfClanExists(String clanName) {
        return clanStorage.exists(clanName);
    }

    public Optional<Clan> find(String clanName) {
        return Optional.of(get(clanName));
    }

    public Optional<Clan> findByPlayer(String playerName) {
        return Optional.of(getByPlayer(playerName));
    }
}
