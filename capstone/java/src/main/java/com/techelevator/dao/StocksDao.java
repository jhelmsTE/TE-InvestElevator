package com.techelevator.dao;

import com.techelevator.model.Stocks;

import java.util.List;

public interface StocksDao {

    List<Stocks> showUserStocksByGame(String username, int gameId);

    Stocks buyStockByTicker(String username, int gameId, String ticker);

    Stocks sellStockByTicker(String username, int gameId, String ticker);


}
