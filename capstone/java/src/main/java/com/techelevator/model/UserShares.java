package com.techelevator.model;

public class UserShares {
    private int gameId;
    private String username;
    private String ticker;
    private int sharesOwned;

    public UserShares(){};

    public UserShares(int gameId, String username, String ticker, int sharesOwned) {
        this.gameId = gameId;
        this.username = username;
        this.ticker = ticker;
        this.sharesOwned = sharesOwned;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public int getSharesOwned() {
        return sharesOwned;
    }

    public void setSharesOwned(int sharesOwned) {
        this.sharesOwned = sharesOwned;
    }

    @Override
    public String toString() {
        return "UserShares{" +
                "gameId=" + gameId +
                ", username='" + username + '\'' +
                ", ticker='" + ticker + '\'' +
                ", sharesOwned=" + sharesOwned +
                '}';
    }


}
