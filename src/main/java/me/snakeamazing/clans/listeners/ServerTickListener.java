package me.snakeamazing.clans.listeners;

import me.snakeamazing.clans.event.ServerTickEvent;
import me.snakeamazing.clans.request.RequestManager;
import me.snakeamazing.clans.tick.ServerTickCause;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ServerTickListener implements Listener {

    private final RequestManager requestManager;

    public ServerTickListener(RequestManager requestManager) {
        this.requestManager = requestManager;
    }

    @EventHandler
    public void onServerTickEvent(ServerTickEvent event) {
        if (event.getServerTickCause() == ServerTickCause.SECOND) {
            requestManager.updatePendingRequests();
        }
    }
}
