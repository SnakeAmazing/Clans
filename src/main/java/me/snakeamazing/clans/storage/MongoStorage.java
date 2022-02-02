package me.snakeamazing.clans.storage;

import me.snakeamazing.clans.storage.model.Model;
import org.bson.conversions.Bson;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface MongoStorage<T extends Model> extends Storage<T> {

    void saveAsync(T object);

    void removeAsync(String id);

    T filterAndGetFirst(Bson filter);

    CompletableFuture<T> filterAndGetFirstAsync(Bson filter);

    CompletableFuture<T> getAsync(String id);

    CompletableFuture<List<T>> sort(Bson sort);

    CompletableFuture<Set<T>> filterAndGet(Bson filter);

}
