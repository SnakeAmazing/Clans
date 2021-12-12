package me.snakeamazing.clans.storage.repository;

import dev.morphia.Datastore;
import me.snakeamazing.clans.provider.Provider;
import me.snakeamazing.clans.storage.objects.Model;

public class ObjectRepositoryProvider<T extends Model> implements Provider<ObjectRepository<T>> {

    private final ObjectRepository<T> objectRepository;

    public ObjectRepositoryProvider(Datastore datastore, Class<T> clazz) {
        this.objectRepository = new MongoObjectRepository<>(datastore, clazz);
    }


    @Override
    public ObjectRepository<T> get() {
        return objectRepository;
    }
}
