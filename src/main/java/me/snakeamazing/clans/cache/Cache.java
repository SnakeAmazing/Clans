package me.snakeamazing.clans.cache;

import java.util.Collection;

public interface Cache<K, V> {

    void add(K key, V value);

    void remove(K key);

    V get(K key);

    boolean exists(K key);

    Collection<V> values();
}
