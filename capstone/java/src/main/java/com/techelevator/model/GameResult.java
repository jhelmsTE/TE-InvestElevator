package com.techelevator.model;

import java.math.BigDecimal;

public class GameResult {
        private int userId;
        private int gameId;
        private String gameName;
        private String userName;
        private BigDecimal cashToTrade = new BigDecimal(100000);
        private BigDecimal investmentValue = new BigDecimal(0);

        public GameResult(){
        }

    public GameResult(int userId, int gameId, String gameName, String userName, BigDecimal cashToTrade,
                      BigDecimal investmentValue) {
        this.userId = userId;
        this.gameId = gameId;
        this.gameName = gameName;
        this.userName = userName;
        this.cashToTrade = cashToTrade;
        this.investmentValue = investmentValue;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getUserId() {
        return userId;
    }

    public int getGameId() {
        return gameId;
    }

    public String getUserName() {
        return userName;
    }

    public BigDecimal getCashToTrade() {
        return cashToTrade;
    }

    public BigDecimal getInvestmentValue() {
        return investmentValue;
    }

    public void setInvestmentValue(BigDecimal investmentValue) {
        this.investmentValue = investmentValue;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCashToTrade(BigDecimal cashToTrade) {
        this.cashToTrade = cashToTrade;
    }


    @Override
    public String toString() {
        return "GameResult{" +
                "userId=" + userId +
                ", gameId=" + gameId +
                ", userName='" + userName + '\'' +
                ", cashToTrade=" + cashToTrade +
                ", totalAccountValue=" + investmentValue +
                '}';
    }
}
