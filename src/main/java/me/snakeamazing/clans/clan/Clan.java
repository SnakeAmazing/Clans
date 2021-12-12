package me.snakeamazing.clans.clan;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.IndexOptions;
import dev.morphia.annotations.Indexed;
import me.snakeamazing.clans.storage.objects.Model;

import java.util.Set;

@Entity(value = "Clans")
public class Clan implements Model {

    @Id
    private String id;

    @Indexed(options = @IndexOptions(unique = true))
    private String name;

    @Indexed(options = @IndexOptions(unique = true))
    private String prefix;

    @Indexed(options = @IndexOptions(unique = true))
    private String leader;

    private int level;
    private Set<String> members;
    private char color;

    public Clan() {

    }

    public String getId() {
        return id;
    }

    public void setId(String clanUUID) {
        this.id = clanUUID;
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

    public Set<String> getMembers() {
        return members;
    }

    public void setMembers(Set<String> members) {
        this.members = members;
    }

    public void addMember(String newMember) {
        this.members.add(newMember);
    }

    public void removeMember(String member) {
        this.members.remove(member);
    }

    public char getColor() {
        return color;
    }

    public void setColor(char color) {
        this.color = color;
    }
}
