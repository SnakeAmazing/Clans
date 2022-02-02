package me.snakeamazing.clans.clan;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.beans.ConstructorProperties;
import java.util.HashSet;
import java.util.Set;

public class DefaultClan implements Clan {

    @JsonProperty("_id")
    private String name;

    private String prefix;

    private String leader;

    private int level;
    private int kills;
    private int deaths;

    private Set<String> members;
    private Set<String> allys;
    private Set<String> enemies;

    private char color;

    public DefaultClan(String name, String prefix, String leader) {
        this.name = name;
        this.prefix = prefix;
        this.leader = leader;

        this.level = 1;
        this.kills = 0;
        this.deaths = 0;
        this.members = new HashSet<>();
        this.allys = new HashSet<>();
        this.enemies = new HashSet<>();
        this.color = '7';
    }

    @ConstructorProperties({
            "name", "prefix", "leader", "level", "kills", "deaths", "members", "allys", "enemies", "color"
    })
    public DefaultClan(String name, String prefix, String leader, int level, int kills, int deaths,
                       Set<String> members, Set<String> allys, Set<String> enemies, char color) {
        this.name = name;
        this.prefix = prefix;
        this.leader = leader;
        this.level = level;
        this.kills = kills;
        this.deaths = deaths;
        this.members = members;
        this.allys = allys;
        this.enemies = enemies;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void incrementLevel() {
        ++this.level;
    }

    @Override
    public int getKills() {
        return kills;
    }

    @Override
    public void setKills(int kills) {
        this.kills = kills;
    }

    @Override
    public void incrementKills() {
        ++this.kills;
    }

    @Override
    public int getDeaths() {
        return deaths;
    }

    @Override
    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    @Override
    public void incrementDeaths() {
        ++this.deaths;
    }

    public Set<String> getMembers() {
        return members;
    }

    @Override
    public Set<String> getAllys() {
        return allys;
    }

    @Override
    public Set<String> getEnemies() {
        return enemies;
    }

    public char getColor() {
        return color;
    }

    public void setColor(char color) {
        this.color = color;
    }

    @Override
    public String getId() {
        return null;
    }
}
