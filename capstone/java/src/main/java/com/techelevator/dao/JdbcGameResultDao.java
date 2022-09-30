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
        int gameId = jdbcTemplate.queryForObject("SELECT game_id FROM games WHERE game_name = ?", int.class, gameName);
        return gameId;
    }

    @Override
    public void createGameResult(GameResult [] gameResults) {
        // gameResult : {
//        userId: 1,
//        gameName: FirstGame
//        username: jhelms
//    }
        
        for (GameResult eachGameResult : gameResults) {

            // call a method that finds gameId by gameName (eachGameResult.getGameName)
            /*
            passed in :
            userid
            gamename
            username
             */
            String name = eachGameResult.getGameName();
            int gameId =  findIdByGameName(name);// result from method
            eachGameResult.setGameId(gameId);
            // filled object complete
        }

        for (GameResult eachGameResult : gameResults) {

            String sql = "insert into game_results (user_id, game_id, game_name " +
                    "username, cash_to_trade, total_account_value) values (?,?,?,?,?,?)";

            jdbcTemplate.update(sql, eachGameResult.getUserId(),
                    eachGameResult.getGameId(), eachGameResult.getUserName(),eachGameResult.getCashToTrade()
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
