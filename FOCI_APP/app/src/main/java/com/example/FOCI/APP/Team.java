/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.FOCI.APP;

/**
 *
 * @author bocsk
 */
import java.util.ArrayList;
import java.util.List;

public class Team {

    private int id;
    private String name;
    private List<Player> players;

    public Team(int id, String name) {
        this.id = id;
        this.name = name;
        this.players = new ArrayList<>();
    }

    public double getTotalMarketValue() {
        double totalValue = 0.0;
        for (Player player : this.players) {
            totalValue += player.getMarketValue_m_euro();
        }
        return totalValue;
    }

    public void addPlayer(Player player) {
        if (player != null && player.getTeamId() == this.id) {
            this.players.add(player);
        }
    }

    public void removePlayer(Player player) {
        this.players.remove(player);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "Team {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", totalMarketValue=" + getTotalMarketValue() + 
                ", playerCount=" + players.size() +
                '}';
    }
}
