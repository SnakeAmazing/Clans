package me.snakeamazing.clans.storage.objects;

import org.bson.types.ObjectId;

public interface Model {

    ObjectId getId();

    void setId(ObjectId id);

}
