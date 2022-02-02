package me.snakeamazing.clans.connection;

public interface Connection<T> {

    void connect();

    void close();

    T get();

}