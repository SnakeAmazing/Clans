package me.snakeamazing.clans.serialize;

public interface ObjectSerializer<T> {

    String serialize(T object);

    T deserialize(String content);

}
