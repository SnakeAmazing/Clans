package me.snakeamazing.clans.module;

import me.snakeamazing.clans.service.ClanService;
import me.snakeamazing.clans.service.UserService;

public class ServiceModule implements Module {

    private final CacheModule cacheModule;
    private final StorageModule storageModule;

    private ClanService clanService;
    private UserService userService;

    public ServiceModule(CacheModule cacheModule, StorageModule storageModule) {
        this.cacheModule = cacheModule;
        this.storageModule = storageModule;
    }

    @Override
    public void start() {
        clanService = new ClanService(cacheModule.getClanCache(), storageModule.getClanStorage(),
                cacheModule.getUserCache());

        userService = new UserService(cacheModule.getUserCache(), storageModule.getClanUserStorage());
    }

    @Override
    public void stop() {

    }

    public ClanService getClanService() {
        return clanService;
    }

    public UserService getUserService() {
        return userService;
    }
}
