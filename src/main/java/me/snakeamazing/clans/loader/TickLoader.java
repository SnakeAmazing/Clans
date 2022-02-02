package me.snakeamazing.clans.loader;

import me.snakeamazing.clans.Clans;
import me.snakeamazing.clans.event.ServerTickEvent;
import me.snakeamazing.clans.tick.ServerTickCause;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class TickLoader implements Loader {

    private static final int SECONDS = 20;
    private static final int MINUTE = 1200;
    private static final int HALF_HOUR = 36000;
    private static final int HOUR = 72000;

    int currentSeconds = SECONDS;
    int currentMinutes = MINUTE;
    int currentHalfHour = HALF_HOUR;
    int currentHour = HOUR;

    private final PluginManager pluginManager;
    private final Clans clans;

    public TickLoader(Clans clans) {
        pluginManager = Bukkit.getPluginManager();

        this.clans = clans;
    }

    @Override
    public void load() {
        Bukkit.getScheduler().runTaskTimer(clans, runnable(), 0, 1);
    }

    private Runnable runnable() {
        return () -> {
            currentSeconds--;
            currentMinutes--;
            currentHalfHour--;
            currentHour--;

            pluginManager.callEvent(new ServerTickEvent(ServerTickCause.TICK));

            if (currentSeconds == 0) {
                currentSeconds = SECONDS;

                pluginManager.callEvent(new ServerTickEvent(ServerTickCause.SECOND));
            }

            if (currentMinutes == 0) {
                currentMinutes = MINUTE;

                pluginManager.callEvent(new ServerTickEvent(ServerTickCause.MINUTE));
            }

            if (currentHalfHour == 0) {
                currentHalfHour = HALF_HOUR;

                pluginManager.callEvent(new ServerTickEvent(ServerTickCause.HALF_HOUR));
            }

            if (currentHour == 0) {
                currentHour = HOUR;

                pluginManager.callEvent(new ServerTickEvent(ServerTickCause.HOUR));
            }
        };
    }

}
