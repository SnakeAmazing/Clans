package me.snakeamazing.clans.storage.repository;

import dev.morphia.Datastore;
import me.snakeamazing.clans.clan.Clan;

import java.util.HashMap;
import java.util.Map;

public class RepositoryMatcher {

    private final Map<Class<?>, ObjectRepository<?>> repositories = new HashMap<>();

    private final Datastore datastore;

    public RepositoryMatcher(Datastore datastore) {
        this.datastore = datastore;

        init();
    }

    public void init() {

        ObjectRepository<Clan> clanObjectProvider = new MongoObjectRepository<>(datastore, Clan.class);

        repositories.put(Clan.class, clanObjectProvider);
    }

    public ObjectRepository<?> get(Class<?> key) {
        return repositories.get(key);
    }
}
