package me.snakeamazing.clans.module;

import me.snakeamazing.clans.clan.ClanManager;
import me.snakeamazing.clans.file.FileMatcher;
import me.snakeamazing.clans.service.ClanService;

public class ManagerModule implements Module {

    private final FileMatcher fileMatcher;
    private final ServiceModule serviceModule;

    private ClanManager clanManager;


    public ManagerModule(ServiceModule serviceModule, FileMatcher matcher) {
        this.serviceModule = serviceModule;
        this.fileMatcher = matcher;
    }

    @Override
    public void start() {
        clanManager = new ClanManager(serviceModule.getClanService(), fileMatcher);
    }

    @Override
    public void stop() {

    }

    public ClanManager getClanManager() {
        return clanManager;
    }
}
