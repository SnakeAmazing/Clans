package me.snakeamazing.clans.clan;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.IndexOptions;
import dev.morphia.annotations.Indexed;
import me.snakeamazing.clans.storage.objects.Model;
import org.bson.types.ObjectId;

import java.util.Collections;
import java.util.Set;

@Entity(value = "clan")
public class Clan implements Model {

    @Id
    private ObjectId id;

    @Indexed(options = @IndexOptions(unique = true))
    private String name;

    @Indexed(options = @IndexOptions(unique = true))
    private String prefix;

    @Indexed(options = @IndexOptions(unique = true))
    private String leader;

    private int level;
    private Set<String> members;
    private char color;

    public Clan(String name, String prefix, String leader) {
        this.id = new ObjectId();
        this.name = name;
        this.prefix = prefix;
        this.leader = leader;

        this.level = 1;
        this.members = Collections.emptySet();
        this.color = '7';
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId clanUUID) {
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
