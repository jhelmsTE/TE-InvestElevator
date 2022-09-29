package com.techelevator.dao;

import com.techelevator.model.Game;

import java.util.Date;
import java.util.List;

public interface GameDao {

    List<Game> findAllGames();

    Game findGameById(int id);

    //create method

    boolean createGame(String username, Date startDate, Date endDate,
                       String gameName, String gameResult);

    void updateGame(int id, Date startDate, Date endDate, String gameName);
}
