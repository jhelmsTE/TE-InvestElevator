package com.techelevator.dao;

import com.techelevator.model.Leaderboard;
import com.techelevator.model.Stocks;
import com.techelevator.model.UserShares;

import java.util.List;

public interface StocksDao {

    List<Stocks> showUserStocksByGame(int id);

    Stocks getCompanyNameByTicker(String ticker);

    List<UserShares> displayShares(int id, String username);

    void createNewStockTransaction(Stocks stocks);

    List<Leaderboard> displayLeaderboard(int gameId);

    void sellAllStocks(Stocks stocks);

}
