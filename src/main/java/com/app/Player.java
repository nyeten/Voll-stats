package com.app;


public class Player {
    String name;
    int number;
    String position;
    // transient to avoid serialization issues
    transient PlayerStats stats;

    public Player(String name, int number, String position) {
        this.name = name;
        this.number = number;
        this.position = position;
     }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public String getPosition() {
        return position;
    }

    public PlayerStats getStats() {
        return stats;
    }
    
    public void setStats(PlayerStats stats) {
        this.stats = stats;
    }
}
