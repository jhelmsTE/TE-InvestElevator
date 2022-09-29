package com.techelevator.dao;

import java.math.BigDecimal;

public interface GameResultDao {
    boolean createGameResult(int userId, int gameId, String userName,
                             BigDecimal cashToTrade, BigDecimal totalAccountValue);

}
