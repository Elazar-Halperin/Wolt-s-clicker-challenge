package com.example.clickercoutner;

public class Clicks {
    int id;
    int counterClicks;
    String time;
    boolean isHighest;

    public Clicks() {
    }

    public Clicks(int id, int counterClicks, String time, boolean isHighest) {
        this.id = id;
        this.counterClicks = counterClicks;
        this.time = time;
        this.isHighest = isHighest;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCounterClicks() {
        return counterClicks;
    }

    public void setCounterClicks(int counterClicks) {
        this.counterClicks = counterClicks;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isHighest() {
        return isHighest;
    }

    public void setHighest(boolean highest) {
        isHighest = highest;
    }
}
