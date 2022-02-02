package me.snakeamazing.clans.cache;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DefaultCache<K, V> implements Cache<K, V> {

    private final Map<K, V> values;

    public DefaultCache() {
        values = new HashMap<>();
    }

    @Override
    public void add(K key, V value) {
        values.put(key, value);
    }

    @Override
    public void remove(K key) {
        values.remove(key);
    }

    @Override
    public V get(K key) {
        return values.get(key);
    }

    @Override
    public boolean exists(K key) {
        return values.containsKey(key);
    }

    @Override
    public Collection<V> values() {
        return values.values();
    }
}
