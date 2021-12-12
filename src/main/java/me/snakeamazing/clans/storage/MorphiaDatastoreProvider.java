package me.snakeamazing.clans.storage;

import com.mongodb.client.MongoClient;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import me.snakeamazing.clans.provider.Provider;

public class MorphiaDatastoreProvider implements Provider<Datastore> {

    private final Datastore datastore;

    public MorphiaDatastoreProvider(MongoClient mongoClient, String databaseName) {
        this.datastore = Morphia.createDatastore(mongoClient, databaseName);
    }

    @Override
    public Datastore get() {
        return datastore;
    }
}
