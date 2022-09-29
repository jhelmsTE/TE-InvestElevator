package com.techelevator.dao;

import com.techelevator.model.Stocks;

import java.util.ArrayList;
import java.util.List;

public class JdbcStocksDao implements StocksDao {


    @Override
    public List<Stocks> showUserStocksByGame(String username, int gameId){
     List<Stocks> stocksList = new ArrayList<>();

     return stocksList;

    }


    @Override
   public Stocks sellStockByTicker(String username, int gameId, String ticker) {
    Stocks stocks = new Stocks();


    return stocks;
}

    @Override
    public Stocks buyStockByTicker(String username, int gameId, String ticker) {
        Stocks stocks = new Stocks();


        return stocks;
    }


}
