package me.snakeamazing.clans.module;

import me.snakeamazing.clans.Clans;
import me.snakeamazing.clans.loader.Loader;
import me.snakeamazing.clans.loader.TickLoader;

public class LoaderModule implements Loader {

    private final Clans clans;

    public LoaderModule(Clans clans) {
        this.clans = clans;
    }

    @Override
    public void load() {
        TickLoader tickLoader = new TickLoader(clans);
        tickLoader.load();

    }
}
