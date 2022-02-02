package me.snakeamazing.clans.module;

import me.snakeamazing.clans.cache.Cache;
import me.snakeamazing.clans.cache.DefaultCache;
import me.snakeamazing.clans.clan.Clan;
import me.snakeamazing.clans.storage.MongoStorage;
import me.snakeamazing.clans.user.ClanUser;

public class CacheModule implements Module {

    private Cache<String, Clan> clanCache;
    private Cache<String, ClanUser> userCache;

    private final MongoStorage<Clan> clanStorage;
    private final MongoStorage<ClanUser> clanUserStorage;

    public CacheModule(MongoStorage<Clan> clanStorage, MongoStorage<ClanUser> clanUserStorage) {
        this.clanStorage = clanStorage;
        this.clanUserStorage = clanUserStorage;
    }

    @Override
    public void start() {
        clanCache = new DefaultCache<>();
        userCache = new DefaultCache<>();
    }

    @Override
    public void stop() {
        clanCache.values().forEach(clanStorage::save);
        userCache.values().forEach(clanUserStorage::save);
    }

    public Cache<String, Clan> getClanCache() {
        return clanCache;
    }

    public Cache<String, ClanUser> getUserCache() {
        return userCache;
    }
}
