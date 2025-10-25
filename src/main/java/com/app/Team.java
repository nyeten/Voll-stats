package com.app;

import java.util.ArrayList;


public class Team {
    
    String name;
    int numPlayers = 0;
    //list of players on team
    ArrayList<Player> players;
    
    public Team(String name) {
        players = new ArrayList<>();
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public int size() {
        return numPlayers;
    }

    //add player to team
    public void addPlayer(String name, int number, String position) {
        //create new player and add to list
        Player newPlayer = new Player(name, number, position);
        numPlayers++;
        players.add(newPlayer);
    }

    public Player getPlayer(int index) {
        return players.get(index);
    }
    
    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void delPlayer(int index) {
        players.remove(index);
        numPlayers--;
    }
}
