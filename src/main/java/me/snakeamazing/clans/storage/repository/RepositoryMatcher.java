package me.snakeamazing.clans.storage.repository;

import dev.morphia.Datastore;
import me.snakeamazing.clans.clan.Clan;
import me.snakeamazing.clans.provider.Provider;
import me.snakeamazing.clans.storage.objects.Model;

import java.util.HashMap;
import java.util.Map;

public class RepositoryMatcher {

    private final Map<String, Provider<ObjectRepository<? extends Model>>> repositories = new HashMap<>();

    private final Datastore datastore;

    public RepositoryMatcher(Datastore datastore) {
        this.datastore = datastore;

        init();
    }

    public void init() {

        Provider<ObjectRepository<Clan>> clanObjectProvider = new ObjectRepositoryProvider<>(datastore, Clan.class);

        repositories.put("clans", clanObjectProvider);
    }

    
}
