package me.snakeamazing.clans.clan;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.beans.ConstructorProperties;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class DefaultClan implements Clan {

    @JsonProperty("_id")
    private String name;

    private String prefix;

    private UUID leader;

    private int level;
    private int kills;
    private int deaths;

    private Set<UUID> members;
    private Set<String> allys;
    private Set<String> enemies;

    private char color;

    public DefaultClan(String name, String prefix, UUID leader) {
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
    public DefaultClan(String name, String prefix, UUID leader, int level, int kills, int deaths,
                       Set<UUID> members, Set<String> allys, Set<String> enemies, char color) {
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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPrefix() {
        return prefix;
    }

    @Override
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public UUID getLeader() {
        return leader;
    }

    @Override
    public void setLeader(UUID leader) {
        this.leader = leader;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
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

    @Override
    public Set<UUID> getAllMembers() {
        Set<UUID> all = members;
        all.add(leader);

        return all;
    }

    @Override
    public void addMember(UUID uuid) {
        this.members.add(uuid);
    }

    @Override
    public void removeMember(UUID uuid) {
        this.members.remove(uuid);
    }

    public Set<UUID> getMembers() {
        return members;
    }

    @Override
    public void setMembers(Set<UUID> members) {
        this.members = members;
    }

    @Override
    public Set<String> getAllys() {
        return allys;
    }

    @Override
    public void setAllys(Set<String> allys) {
        this.allys = allys;
    }

    @Override
    public Set<String> getEnemies() {
        return enemies;
    }

    @Override
    public void setEnemies(Set<String> enemies) {
        this.enemies = enemies;
    }

    public char getColor() {
        return color;
    }

    public void setColor(char color) {
        this.color = color;
    }

    @Override
    public String getId() {
        return prefix;
    }
}
