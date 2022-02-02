package me.snakeamazing.clans.user;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import me.snakeamazing.clans.rank.ClanRank;
import me.snakeamazing.clans.storage.model.Model;

@JsonDeserialize(as = DefaultClanUser.class)
public interface ClanUser extends Model {

    String getName();

    void setName(String name);

    String getUniqueId();

    void setUniqueId(String uuid);

    String getClanName();

    ClanRank getRole();

    void setRole(ClanRank clanRank);

    void setClanName(String clanName);

    void setAllyChat(boolean allyChat);

    boolean isOnAllyChat();
}
