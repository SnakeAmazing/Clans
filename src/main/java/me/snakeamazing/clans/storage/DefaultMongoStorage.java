package me.snakeamazing.clans.storage;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import me.snakeamazing.clans.file.FileMatcher;
import me.snakeamazing.clans.file.YAMLFile;
import me.snakeamazing.clans.serialize.JacksonSerializer;
import me.snakeamazing.clans.serialize.ObjectSerializer;
import me.snakeamazing.clans.storage.model.Model;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.*;
import java.util.concurrent.CompletableFuture;

public class DefaultMongoStorage<T extends Model> implements MongoStorage<T> {

    private final MongoClient mongoClient;

    private final YAMLFile config;

    private MongoDatabase mongoDatabase;
    private final Class<T> clazz;
    private final ObjectSerializer<T> objectSerialize;
    private MongoCollection<Document> mongoCollection;

    public DefaultMongoStorage(MongoClient mongoClient, Class<T> clazz, YAMLFile config) {
        this.mongoClient = mongoClient;
        this.clazz = clazz;
        this.objectSerialize = new JacksonSerializer<>(clazz);

        this.config = config;
    }

    @Override
    public void save(T object) {
        MongoCollection<Document> collection = getCollection();
        remove(object.getId());
        collection.insertOne(Document.parse(objectSerialize.serialize(object)));
    }

    @Override
    public void saveAsync(T object) {
        CompletableFuture.runAsync(() -> save(object));
    }

    @Override
    public void remove(String id) {
        getCollection().findOneAndDelete(Filters.eq("_id", id));
    }

    @Override
    public void removeAsync(String id) {
        CompletableFuture.runAsync(() -> remove(id));
    }

    @Override
    public T filterAndGetFirst(Bson filter) {
        return transform(getCollection().find(filter).first());
    }

    public CompletableFuture<T> filterAndGetFirstAsync(Bson filter) {
        return CompletableFuture.supplyAsync(() -> filterAndGetFirst(filter));
    }

    @Override
    public CompletableFuture<T> getAsync(String id) {
        return CompletableFuture.supplyAsync(() -> get(id));
    }

    @Override
    public CompletableFuture<List<T>> sort(Bson sort) {
        return CompletableFuture.supplyAsync(() -> {
            List<T> list = new ArrayList<>();

            for (Document document : getCollection().find().sort(sort)) {
                list.add(transform(document));
            }

            return list;
        });
    }

    @Override
    public CompletableFuture<Set<T>> filterAndGet(Bson filter) {
        return CompletableFuture.supplyAsync(() -> {
            Set<T> values = new HashSet<>();

            for (Document document : getCollection().find(filter)) {
                values.add(transform(document));
            }
            return values;
        });
    }

    @Override
    public boolean exists(String id) {
        return get(id) != null;
    }

    @Override
    public T get(String id) {
        return transform(getCollection().find(Filters.eq("_id", id)).first());
    }

    @Override
    public Collection<T> values() {
        Set<T> values = new HashSet<>();

        CompletableFuture.runAsync(() -> {
            for (Document document : getCollection().find()) {
                values.add(transform(document));
            }
        });

        return values;
    }

    private MongoCollection<Document> getCollection() {
        if (mongoDatabase == null) {
            mongoDatabase = mongoClient.getDatabase(config.getString("database.database-name"));
        }

        if (mongoCollection == null) {
            mongoCollection = mongoDatabase.getCollection(clazz.getSimpleName());
        }

        return mongoCollection;
    }

    private T transform(Document document) {

        if (document == null) {
            return null;
        }

        return objectSerialize.deserialize(document.toJson());
    }

}

