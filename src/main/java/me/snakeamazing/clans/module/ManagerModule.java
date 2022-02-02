package me.snakeamazing.clans.module;

import me.snakeamazing.clans.clan.ClanHandler;
import me.snakeamazing.clans.clan.ClanManager;
import me.snakeamazing.clans.file.FileMatcher;
import me.snakeamazing.clans.request.RequestManager;
import me.snakeamazing.clans.service.ClanService;

public class ManagerModule implements Module {

    private final FileMatcher fileMatcher;
    private final ServiceModule serviceModule;

    private ClanManager clanManager;
    private RequestManager requestManager;

    private ClanHandler clanHandler;

    public ManagerModule(ServiceModule serviceModule, FileMatcher matcher) {
        this.serviceModule = serviceModule;
        this.fileMatcher = matcher;
    }

    @Override
    public void start() {
        clanManager = new ClanManager(serviceModule.getClanService(), fileMatcher);
        requestManager = new RequestManager();

        clanHandler = new ClanHandler(clanManager, requestManager, fileMatcher);
    }

    @Override
    public void stop() {

    }

    public ClanManager getClanManager() {
        return clanManager;
    }

    public ClanHandler getClanHandler() {
        return clanHandler;
    }

    public RequestManager getRequestManager() {
        return requestManager;
    }
}
