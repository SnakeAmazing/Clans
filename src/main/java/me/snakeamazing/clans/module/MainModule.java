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
import me.snakeamazing.clans.storage.repository.RepositoryMatcher;

public class MainModule implements Module {

    private final Clans clans;

    private StorageModule storageModule;

    private RepositoryMatcher repositoryMatcher;

    private ClanManager clanManager;

    public MainModule(Clans clans) {
        this.clans = clans;
    }

    public void load() {
        FileMatcher fileMatcher = new FileMatcher(clans);

        storageModule = new StorageModule();
        storageModule.load();

        System.out.println("Connected to mongodb");

        repositoryMatcher = storageModule.getRepositoryMatcher();

        clanManager = new ClanManager(fileMatcher, repositoryMatcher);

        registerCommands();
    }

    public void unLoad() {

    }

    private void registerCommands() {
        PartInjector partInjector = new SimplePartInjector();
        partInjector.install(new DefaultsModule());
        partInjector.install(new BukkitModule());

        AnnotatedCommandTreeBuilder treeBuilder = new AnnotatedCommandTreeBuilderImpl(partInjector);
        CommandManager commandManager = new BukkitCommandManager(clans.getName());

        commandManager.registerCommands(treeBuilder.fromClass(new ClanCommand(clanManager)));
    }
}
