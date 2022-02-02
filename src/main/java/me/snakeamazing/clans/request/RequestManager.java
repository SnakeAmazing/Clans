package me.snakeamazing.clans.request;

import me.snakeamazing.clans.event.RequestExpireEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RequestManager {

    private final Map<String, Request> pendingRequests;

    public RequestManager() {
        this.pendingRequests = new ConcurrentHashMap<>();
    }

    public void addRequest(Request request) {
        if (request instanceof ClanInviteRequest) {
            ClanInviteRequest inviteRequest = (ClanInviteRequest) request;
            pendingRequests.put(inviteRequest.getTarget().getUniqueId().toString(), inviteRequest);
            return;
        }
    }

    public void removeRequest(Player player) {
        pendingRequests.remove(player.getUniqueId().toString());
    }

    public boolean hasPendingRequest(Player player) {
        return pendingRequests.containsKey(player.getUniqueId().toString());
    }

    public Request getPendingRequest(Player player) {
        return pendingRequests.get(player.getUniqueId().toString());
    }

    public void updatePendingRequests() {
        pendingRequests.forEach((s, request) -> {
            request.decreaseTime();
            if (request.getTime() <= 0) {
                Bukkit.getPluginManager().callEvent(new RequestExpireEvent(request));
            }
        });
    }
}
