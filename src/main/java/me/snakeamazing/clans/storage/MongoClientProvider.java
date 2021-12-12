package me.snakeamazing.clans.storage;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoClientProvider {

    private final MongoClient mongoClient;

    public MongoClientProvider(String uri) {
        this.mongoClient = MongoClients.create(uri);
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }
}
