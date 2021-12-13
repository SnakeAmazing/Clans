package me.snakeamazing.clans.storage;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoClientProvider {

    private MongoClient mongoClient;
    private final String uri;

    public MongoClientProvider(String uri) {
        this.uri = uri;

        init();
    }

    public void init() {
        ConnectionString connectionString =
                new ConnectionString("mongodb+srv://test:testtest@cluster0.hs8xp.mongodb.net/clans?retryWrites=true&w=majority");

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        mongoClient = MongoClients.create(settings);
   }

    public MongoClient getMongoClient() {
        return mongoClient;
    }
}
