package me.snakeamazing.clans;

import me.fixeddev.commandflow.CommandManager;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilder;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilderImpl;
import me.fixeddev.commandflow.annotated.part.PartInjector;
import me.fixeddev.commandflow.annotated.part.SimplePartInjector;
import me.fixeddev.commandflow.annotated.part.defaults.DefaultsModule;
import me.fixeddev.commandflow.bukkit.BukkitCommandManager;
import me.fixeddev.commandflow.bukkit.factory.BukkitModule;
import me.snakeamazing.clans.commands.ClanCommand;
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
