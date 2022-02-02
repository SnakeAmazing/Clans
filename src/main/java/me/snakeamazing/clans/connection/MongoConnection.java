package me.snakeamazing.clans.connection;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoConnection implements Connection<MongoClient> {

    private final String uri;
    private MongoClient mongoClient;

    public MongoConnection(String uri) {
        this.uri = uri;
    }

    @Override
    public void connect() {
        mongoClient = new MongoClient(new MongoClientURI(uri));
    }

    @Override
    public void close() {
        mongoClient.close();
    }

    @Override
    public MongoClient get() {
        return mongoClient;
    }

}
