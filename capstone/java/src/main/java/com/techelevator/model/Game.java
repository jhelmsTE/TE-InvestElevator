package com.techelevator.model;

import java.util.Date;

public class Game {
    //add @NotBlank for required fields ('not null' in SQL)
    private int id;
    private String username;
    private Date startDate;
    private Date endDate;
    private String gameName;
    private String gameResult;

    public Game (){}

    public Game (String username, Date startDate, Date endDate, String gameName, String gameResult){
        this.username = username;
        this.startDate = startDate;
        this.endDate = endDate;
        this.gameName = gameName;
        this.gameResult = gameResult;
    }

    public Game(Object object) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameResult() {
        return gameResult;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", gameName='" + gameName + '\'' +
                ", gameResult='" + gameResult + '\'' +
                '}';
    }

    public void setGameResult(String gameResult) {
        this.gameResult = gameResult;
    }
}
