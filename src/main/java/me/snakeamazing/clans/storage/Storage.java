package me.snakeamazing.clans.storage;

import me.snakeamazing.clans.storage.model.Model;

import java.util.Collection;

public interface Storage<T extends Model> {

    void save(T object);

    void remove(String id);

    boolean exists(String id);

    T get(String id);

    Collection<T> values();

}
