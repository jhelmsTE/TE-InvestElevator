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
    public int findIdByGameName(String gameName) {
        Integer id = 0;
       String sql = "SELECT game_id FROM games WHERE game_name = ?";
       SqlRowSet gameId = jdbcTemplate.queryForRowSet(sql, gameName);
       if(gameId.next()){
           id = gameId.getInt("game_id");
       }
       return id;
    }

    @Override
    public void createGameResult(GameResult [] gameResults) {

        for (GameResult eachGameResult : gameResults) {

            // call a method that finds gameId by gameName (eachGameResult.getGameName)

            String name = eachGameResult.getGameName();
            int gameId =  findIdByGameName(name);// result from method
            eachGameResult.setGameId(gameId);
            // filled object complete
        }

        for (GameResult eachGameResult : gameResults) {

            String sql = "INSERT into game_results (user_id, game_id, username, " +
                    "game_name, cash_to_trade, total_account_value) VALUES (?,?,?,?,?,?)";

            jdbcTemplate.update(sql, eachGameResult.getUserId(),
                    eachGameResult.getGameId(), eachGameResult.getUserName(), eachGameResult.getGameName(), eachGameResult.getCashToTrade()
                    ,eachGameResult.getTotalAccountValue());
        }

    }

    private GameResult mapRowToGameResult(SqlRowSet rs){
        GameResult gameResult = new GameResult();
        gameResult.setUserId(rs.getInt("user_id"));
        gameResult.setGameId(rs.getInt("game_id"));
        gameResult.setGameName(rs.getString("game_name"));
        gameResult.setUserName(rs.getString("username"));
        gameResult.setCashToTrade(rs.getBigDecimal("cash_to_trade"));
        gameResult.setCashToTrade(rs.getBigDecimal("total_account_value"));
        return gameResult;
    }
}
