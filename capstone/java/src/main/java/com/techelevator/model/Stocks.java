package com.techelevator.model;

import java.math.BigDecimal;

public class Stocks {

    private String username;
    private int gameId;
    private String ticker;
    private String companyName;
    private BigDecimal buyPrice;

    public Stocks() {
    }

    public Stocks(String username, int gameId, String ticker, String companyName, BigDecimal buyPrice) {
        this.username = username;
        this.gameId = gameId;
        this.ticker = ticker;
        this.companyName = companyName;
        this.buyPrice = buyPrice;
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

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }



    @Override
    public String toString() {
        return "Stocks{" +
                "username=" + username +
                ", gameId='" + gameId + '\'' +
                ", ticker='" + ticker + '\'' +
                ", companyName='" + companyName + '\'' +
                ", buyPrice='" + buyPrice + '\'' +
                '}';
    }


}


/*

KEY -> How To Read The Results From the Polygon API
----------------------------------------------------
String API_KEY = J8Q9pZADexcYKvsxHJcBJKNPWCwoEh7g;


Response Attributes
{
tickerstring
The exchange symbol that this item is traded under.

queryCount integer
The number of aggregates (minute or day) used to generate the response.

resultsCount integer
The total number of results for this request.

adjusted boolean
Whether or not this response was adjusted for splits.


resultsarray [
T string
The exchange symbol that this item is traded under.

c number
The close price for the symbol in the given time period.

h number
The highest price for the symbol in the given time period.

l number
The lowest price for the symbol in the given time period.

n integer
The number of transactions in the aggregate window.

o number
The open price for the symbol in the given time period.

otc boolean
Whether or not this aggregate is for an OTC ticker. This field will be left off if false.

tinteger
The Unix Msec timestamp for the start of the aggregate window.

v number
The trading volume of the symbol in the given time period.

vw number
The volume-weighted average price.

status string
The status of this request's response


request_id string
A request id assigned by the server.

 */