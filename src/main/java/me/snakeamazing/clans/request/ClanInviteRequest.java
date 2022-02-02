package me.snakeamazing.clans.request;

import org.bukkit.entity.Player;

public class ClanInviteRequest extends Request {

    private final Player target;

    public ClanInviteRequest(int time, Player target) {
        super(time);

        this.target = target;
    }

    public Player getTarget() {
        return target;
    }
}
