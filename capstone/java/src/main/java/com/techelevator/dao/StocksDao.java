package com.techelevator.dao;

import com.techelevator.model.Stocks;
import com.techelevator.model.UserShares;

import java.util.List;

public interface StocksDao {

    List<Stocks> showUserStocksByGame(int id);

    Stocks getCompanyNameByTicker(String ticker);

    List<UserShares> displayShares(int id);

    void createNewStockTransaction(Stocks stocks);

    Stocks displayLeaderboard(int gameId);

}
