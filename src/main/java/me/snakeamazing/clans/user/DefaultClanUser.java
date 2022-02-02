package me.snakeamazing.clans.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import me.snakeamazing.clans.rank.ClanRank;

import java.beans.ConstructorProperties;

public class DefaultClanUser implements ClanUser {

    @JsonProperty("_id")
    private String name;
    private String uuid;
    private String clanName;

    private String clanRank;

    private boolean allyChat;

    public DefaultClanUser(String name, String uuid, String clanName, ClanRank clanRank) {
        this.name = name;
        this.uuid = uuid;

        this.clanName = clanName;
        this.clanRank = clanRank.getRank();
        this.allyChat = false;
    }

    @ConstructorProperties({
            "name", "uuid", "clanName", "rank", "allyChat"
    })
    public DefaultClanUser(String name, String uuid, String clanName, String clanRank, boolean allyChat) {
        this.name = name;
        this.uuid = uuid;

        this.clanName = clanName;
        this.clanRank = clanRank;
        this.allyChat = allyChat;
    }

    @Override
    public String getId() {
        return name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getUniqueId() {
        return uuid;
    }

    @Override
    public void setUniqueId(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String getClanName() {
        return clanName;
    }

    @Override
    public ClanRank getRole() {
        return ClanRank.valueOf(clanRank);
    }

    @Override
    public void setRole(ClanRank clanRank) {
        this.clanRank = clanRank.getRank();
    }

    @Override
    public void setClanName(String clanName) {
        this.clanName = clanName;
    }

    @Override
    public void setAllyChat(boolean allyChat) {
        this.allyChat = allyChat;
    }

    @Override
    public boolean isOnAllyChat() {
        return allyChat;
    }
}
