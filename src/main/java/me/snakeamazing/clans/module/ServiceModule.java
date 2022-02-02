package me.snakeamazing.clans.module;

import me.snakeamazing.clans.cache.Cache;
import me.snakeamazing.clans.clan.Clan;
import me.snakeamazing.clans.service.ClanService;
import me.snakeamazing.clans.storage.MongoStorage;
import me.snakeamazing.clans.user.ClanUser;

public class ServiceModule implements Module {

    private final CacheModule cacheModule;
    private final StorageModule storageModule;

    private ClanService clanService;

    public ServiceModule(CacheModule cacheModule, StorageModule storageModule) {
        this.cacheModule = cacheModule;
        this.storageModule = storageModule;
    }

    @Override
    public void start() {
        clanService = new ClanService(cacheModule.getClanCache(), storageModule.getClanStorage(),
                cacheModule.getUserCache());
    }

    @Override
    public void stop() {

    }

    public ClanService getClanService() {
        return clanService;
    }
}
