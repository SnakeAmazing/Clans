package me.snakeamazing.clans;

import me.snakeamazing.clans.module.MainModule;
import org.bukkit.plugin.java.JavaPlugin;

public class Clans extends JavaPlugin {

    private MainModule mainModule;

    public void onEnable() {
        mainModule = new MainModule(this);
        mainModule.load();
    }

    public void onDisable() {
        mainModule.unLoad();
    }
}
