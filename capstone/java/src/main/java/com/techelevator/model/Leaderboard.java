package com.techelevator.model;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


public class Leaderboard {

    private  JdbcTemplate jdbcTemplate;



    private String username;
    private int gameId;
    private BigDecimal portfolioValue;


    public Leaderboard(String username, int gameId,
                       BigDecimal portfolioValue) {
        this.username = username;
        this.gameId = gameId;
        this.portfolioValue = portfolioValue;
    }

    public Leaderboard() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public BigDecimal getPortfolioValue() {
        return portfolioValue;
    }

    public void setPortfolioValue(BigDecimal portfolioValue) {
        this.portfolioValue = portfolioValue;
    }




    @Override
    public String toString() {
        return "Leaderboard{" +
                "username='" + username + '\'' +
                ", gameId=" + gameId +
                ", portfolioValue=" + portfolioValue +
                '}';
    }
}
