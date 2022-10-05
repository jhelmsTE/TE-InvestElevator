package com.techelevator.dao;

import com.techelevator.model.Game;
import com.techelevator.model.Stocks;
import com.techelevator.model.UserShares;

import java.util.Date;
import java.util.List;

public interface GameDao {

    List<Game> findAllGames();

    Game findGameById(int id);

    //create method

    boolean createGame(String username, Date startDate, Date endDate,
                       String gameName, String gameResult);

    void updateGame(int id, Date startDate, Date endDate, String gameName);

    Date changeCurrentDate(Game game, int id, Stocks stocks);
}
