package com.techelevator.dao;

import com.techelevator.model.GameResult;

import java.math.BigDecimal;
import java.util.List;

public interface GameResultDao {
    void createGameResult(GameResult [] gameResults);

     int findIdByGameName(String gameName);

     List<GameResult> findGameResultById(int id);

     List<GameResult> findGameResultByCurrentUser(String userName);

     List<GameResult> findGameResultByNotCurrentUser(String userName);
}
