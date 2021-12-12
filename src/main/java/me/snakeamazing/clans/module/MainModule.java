package me.snakeamazing.clans.module;

import me.snakeamazing.clans.Clans;
import me.snakeamazing.clans.file.FileMatcher;

public class MainModule implements Module {

    private final Clans clans;

    public MainModule(Clans clans) {
        this.clans = clans;
    }

    public void load() {
        FileMatcher fileMatcher = new FileMatcher(clans);

    }

    public void unLoad() {

    }
}
