package com.techelevator.dao;

import com.techelevator.model.GameResult;

import java.math.BigDecimal;

public interface GameResultDao {
    void createGameResult(GameResult [] gameResults);

    int findIdByGameName(String gameName);

}
