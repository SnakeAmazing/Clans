package me.snakeamazing.clans.module;

import me.fixeddev.commandflow.CommandManager;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilder;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilderImpl;
import me.fixeddev.commandflow.annotated.part.PartInjector;
import me.fixeddev.commandflow.annotated.part.SimplePartInjector;
import me.fixeddev.commandflow.annotated.part.defaults.DefaultsModule;
import me.fixeddev.commandflow.bukkit.BukkitCommandManager;
import me.fixeddev.commandflow.bukkit.factory.BukkitModule;
import me.snakeamazing.clans.Clans;
import me.snakeamazing.clans.clan.ClanManager;
import me.snakeamazing.clans.commands.ClanCommand;
import me.snakeamazing.clans.file.FileMatcher;

public class MainModule implements Module {

    private final Clans clans;

    private StorageModule storageModule;
    private CacheModule cacheModule;
    private ServiceModule serviceModule;
    private ManagerModule managerModule;

    public MainModule(Clans clans) {
        this.clans = clans;
    }

    public void start() {
        FileMatcher fileMatcher = new FileMatcher(clans);

        storageModule = new StorageModule(fileMatcher);
        storageModule.start();

        cacheModule = new CacheModule(storageModule.getClanStorage(), storageModule.getClanUserStorage());
        cacheModule.start();

        System.out.println("Connected to mongodb");

        serviceModule = new ServiceModule(cacheModule, storageModule);
        serviceModule.start();

        managerModule = new ManagerModule(serviceModule, fileMatcher);
        managerModule.start();

        registerCommands();
    }

    public void stop() {
        storageModule.stop();
        cacheModule.stop();
    }

    private void registerCommands() {
        PartInjector partInjector = new SimplePartInjector();
        partInjector.install(new DefaultsModule());
        partInjector.install(new BukkitModule());

        AnnotatedCommandTreeBuilder treeBuilder = new AnnotatedCommandTreeBuilderImpl(partInjector);
        CommandManager commandManager = new BukkitCommandManager(clans.getName());

        commandManager.registerCommands(treeBuilder.fromClass(new ClanCommand(managerModule.getClanManager())));
    }
}
