package com.techelevator.dao;

import com.techelevator.model.GameResult;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class JdbcGameResultDao implements GameResultDao {
    private final JdbcTemplate jdbcTemplate;
    public JdbcGameResultDao(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}


    @Override
    public boolean createGameResult(int userId, int gameId, String userName,
                                    BigDecimal cashToTrade, BigDecimal totalAccountValue) {
        String sql = "insert into game_results (user_id, game_id, " +
                "username, cash_to_trade, total_account_value) values (?,?,?,?,?)";

        return jdbcTemplate.update(sql, userId, gameId, userName, cashToTrade, totalAccountValue) == 1;
    }


    private GameResult mapRowToGameResult(SqlRowSet rs){
        GameResult gameResult = new GameResult();
        gameResult.setUserId(rs.getInt("user_id"));
        gameResult.setGameId(rs.getInt("game_id"));
        gameResult.setUserName(rs.getString("username"));
        gameResult.setCashToTrade(rs.getBigDecimal("cash_to_trade"));
        gameResult.setCashToTrade(rs.getBigDecimal("total_account_value"));
        return gameResult;
    }
}
