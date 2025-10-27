/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.FOCI.APP;

/**
 *
 * @author bocsk
 */
public class Player {

    private String name;
    private int jerseyNumber;
    private String position;
    private int teamId; 
    private int goals;
    private int assists;
    private int yellowCards;
    private double marketValue_m_euro;

    public Player(String name, int jerseyNumber, String position, int teamId, 
                  int goals, int assists, int yellowCards, double marketValue_m_euro) {
        this.name = name;
        this.jerseyNumber = jerseyNumber;
        this.position = position;
        this.teamId = teamId;
        this.goals = goals;
        this.assists = assists;
        this.yellowCards = yellowCards;
        this.marketValue_m_euro = marketValue_m_euro;
    }

    public double calculatePerformanceScore() {
        return (this.goals * 3.0) + (this.assists * 2.0) - (this.yellowCards * 1.0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public void setJerseyNumber(int jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getYellowCards() {
        return yellowCards;
    }

    public void setYellowCards(int yellowCards) {
        this.yellowCards = yellowCards;
    }

    public double getMarketValue_m_euro() {
        return marketValue_m_euro;
    }

    public void setMarketValue_m_euro(double marketValue_m_euro) {
        this.marketValue_m_euro = marketValue_m_euro;
    }

    @Override
    public String toString() {
        return "Player {" +
                "name='" + name + '\'' +
                ", jerseyNumber=" + jerseyNumber +
                ", position='" + position + '\'' +
                ", teamId=" + teamId +
                ", goals=" + goals +
                ", assists=" + assists +
                ", yellowCards=" + yellowCards +
                ", marketValue_m_euro=" + marketValue_m_euro +
                '}';
    }
}
