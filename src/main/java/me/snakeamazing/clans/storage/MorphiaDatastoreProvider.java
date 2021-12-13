package me.snakeamazing.clans.storage;

import com.mongodb.client.MongoClient;
import dev.morphia.Datastore;
import dev.morphia.Morphia;

public class MorphiaDatastoreProvider {

    private final Datastore datastore;

    public MorphiaDatastoreProvider(MongoClient mongoClient, String databaseName) {
        this.datastore = Morphia.createDatastore(mongoClient, databaseName);
    }

    public Datastore get() {
        return datastore;
    }
}
