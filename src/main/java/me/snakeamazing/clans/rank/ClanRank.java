package me.snakeamazing.clans.rank;

public enum ClanRank {
    LEADER("LIDER"),
    MOD("MOD"),
    USER("USER")
    ;

    private final String rank;

    ClanRank(String rank) {
        this.rank = rank;
    }

    public String getRank() {
        return rank;
    }
}
