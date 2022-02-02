package me.snakeamazing.clans.module;

import com.mongodb.MongoClient;
import me.snakeamazing.clans.clan.Clan;
import me.snakeamazing.clans.connection.Connection;
import me.snakeamazing.clans.connection.MongoConnection;
import me.snakeamazing.clans.file.FileMatcher;
import me.snakeamazing.clans.file.YAMLFile;
import me.snakeamazing.clans.storage.DefaultMongoStorage;
import me.snakeamazing.clans.storage.MongoStorage;
import me.snakeamazing.clans.user.ClanUser;

public class StorageModule implements Module {

    private final YAMLFile config;

    private MongoClient mongoClient;

    private MongoStorage<Clan> clanStorage;
    private MongoStorage<ClanUser> clanUserStorage;

    public StorageModule(FileMatcher matcher) {
        this.config = matcher.getFile("config");
    }

    @Override
    public void start() {
        Connection<MongoClient> connection = new MongoConnection(config.getString("database.uri"));
        connection.connect();

        mongoClient = connection.get();

        clanStorage = new DefaultMongoStorage<>(mongoClient, Clan.class, config);
        clanUserStorage = new DefaultMongoStorage<>(mongoClient, ClanUser.class, config);
    }

    @Override
    public void stop() {
        mongoClient.close();
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public MongoStorage<Clan> getClanStorage() {
        return clanStorage;
    }

    public MongoStorage<ClanUser> getClanUserStorage() {
        return clanUserStorage;
    }
}
