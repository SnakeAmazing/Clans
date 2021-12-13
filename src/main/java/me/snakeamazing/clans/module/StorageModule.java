package me.snakeamazing.clans.module;

import com.mongodb.client.MongoClient;
import dev.morphia.Datastore;
import me.snakeamazing.clans.storage.MongoClientProvider;
import me.snakeamazing.clans.storage.MorphiaDatastoreProvider;
import me.snakeamazing.clans.storage.repository.RepositoryMatcher;

public class StorageModule implements Module {

    private MongoClient mongoClient;
    private Datastore datastore;

    private RepositoryMatcher repositoryMatcher;

    @Override
    public void load() {
        mongoClient = new MongoClientProvider("localhost").getMongoClient();
        datastore = new MorphiaDatastoreProvider(mongoClient, "clans").get();

        repositoryMatcher = new RepositoryMatcher(datastore);
    }

    @Override
    public void unLoad() {

    }

    public RepositoryMatcher getRepositoryMatcher() {
        return repositoryMatcher;
    }
}
