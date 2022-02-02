package me.snakeamazing.clans.listeners;

import me.snakeamazing.clans.event.RequestExpireEvent;
import me.snakeamazing.clans.request.ClanInviteRequest;
import me.snakeamazing.clans.request.Request;
import me.snakeamazing.clans.request.RequestManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class RequestExpireListener implements Listener {

    private final RequestManager requestManager;

    public RequestExpireListener(RequestManager requestManager) {
        this.requestManager = requestManager;
    }

    @EventHandler
    public void onRequestExpireEvent(RequestExpireEvent event) {
        Request request = event.getRequest();

        if (request instanceof ClanInviteRequest) {
            ClanInviteRequest inviteRequest = (ClanInviteRequest) request;
            requestManager.removeRequest(inviteRequest.getTarget());
        }
    }
}
