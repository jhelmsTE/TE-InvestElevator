package com.techelevator.dao;

import com.techelevator.model.Stocks;

import java.util.List;

public interface StocksDao {

    List<Stocks> showUserStocksByGame(String username, int gameId);

    Stocks getCompanyNameByTicker(String ticker);



//    Stocks sellStockByTicker(String username, int gameId, String ticker);

    void createNewStockTransaction(Stocks stocks);



}
