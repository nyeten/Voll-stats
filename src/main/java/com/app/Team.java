package com.app;

import java.util.ArrayList;


public class Team {
    
    String name;
    int numPlayers = 0;
    ArrayList<Player> players;
    int index = 0;
    
    public Team(String name) {
        players = new ArrayList<>();
        this.name = name;
        //this.players = new List<>();
    }

    public String getName() {
        return name;
    }
    
    public int size() {
        return numPlayers;
    }

    // public LinkedList<Player> getPlayers() {
    //     return players;
    // }

    public void addPlayer(String name, int number, String position) {
        Player newPlayer = new Player(name, number, position);
        numPlayers++;
        players.add(newPlayer);
    }

    public Player getPlayer(int index) {
        return players.get(index);
    }

    public void setName(String name) {
        this.name = name;;
    }
}
