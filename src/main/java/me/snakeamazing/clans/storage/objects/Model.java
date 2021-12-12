package me.snakeamazing.clans.storage.objects;

public interface Model {

    String getId();

    default String getCacheIdentifier() {
        return getId();
    }

}
