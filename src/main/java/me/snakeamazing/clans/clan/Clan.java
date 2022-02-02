package me.snakeamazing.clans.clan;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import me.snakeamazing.clans.storage.model.Model;

import java.util.Set;

@JsonDeserialize(as = DefaultClan.class)
public interface Clan extends Model {

    String getName();

    void setName(String name);

    String getPrefix();

    void setPrefix(String prefix);

    String getLeader();

    void setLeader(String leader);

    int getLevel();

    void setLevel(int level);

    void incrementLevel();

    int getKills();

    void setKills(int kills);

    void incrementKills();

    int getDeaths();

    void setDeaths(int deaths);

    void incrementDeaths();

    Set<String> getMembers();

    Set<String> getAllys();

    Set<String> getEnemies();

    char getColor();

    void setColor(char color);
}
