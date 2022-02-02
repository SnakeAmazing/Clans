package me.snakeamazing.clans.request;

public class Request {

    private int time;

    public Request(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public void decreaseTime() {
        --time;
    }
}
