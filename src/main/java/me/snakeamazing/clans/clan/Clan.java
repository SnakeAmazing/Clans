package me.snakeamazing.clans.clan;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import me.snakeamazing.clans.storage.model.Model;

import java.util.Set;
import java.util.UUID;

@JsonDeserialize(as = DefaultClan.class)
public interface Clan extends Model {

    String getName();

    void setName(String name);

    String getPrefix();

    void setPrefix(String prefix);

    UUID getLeader();

    void setLeader(UUID leader);

    int getLevel();

    void setLevel(int level);

    void incrementLevel();

    int getKills();

    void setKills(int kills);

    void incrementKills();

    int getDeaths();

    void setDeaths(int deaths);

    void incrementDeaths();

    Set<UUID> getAllMembers();

    void addMember(UUID uuid);

    void removeMember(UUID uuid);

    Set<UUID> getMembers();

    void setMembers(Set<UUID> members);

    Set<String> getAllys();

    void setAllys(Set<String> allys);

    Set<String> getEnemies();

    void setEnemies(Set<String> enemies);

    char getColor();

    void setColor(char color);
}
