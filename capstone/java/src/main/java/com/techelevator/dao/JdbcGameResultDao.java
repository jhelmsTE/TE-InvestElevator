package com.techelevator.dao;

import com.techelevator.model.GameNotFoundException;
import com.techelevator.model.GameResult;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
    public List<GameResult> findGameResultById(int id) {
        List<GameResult> gameResultsList = new ArrayList<>();
        String sql = "SELECT user_id, game_id, username, game_name, cash_to_trade, investment_value" +
                " FROM game_results WHERE game_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        while(results.next()) {
            GameResult gameResult = mapRowToGameResult(results);
            gameResultsList.add(gameResult);
        }
        return gameResultsList;
    }

    @Override
    public void createGameResult(GameResult [] gameResults) {

        for (GameResult eachGameResult : gameResults) {

            String name = eachGameResult.getGameName();
            int gameId =  findIdByGameName(name);// result from method
            eachGameResult.setGameId(gameId);
            // filled object complete
        }

        for (GameResult eachGameResult : gameResults) {

            String sql = "INSERT into game_results (user_id, game_id, username, " +
                    "game_name, cash_to_trade, investment_value) VALUES (?,?,?,?,?,?)";

            jdbcTemplate.update(sql, eachGameResult.getUserId(),
                    eachGameResult.getGameId(), eachGameResult.getUserName(), eachGameResult.getGameName(), eachGameResult.getCashToTrade()
                    ,eachGameResult.getInvestmentValue());
        }

    }

    @Override
    public List<GameResult> findGameResultByCurrentUser(String userName) {
        List<GameResult> gameResultsListByUser = new ArrayList<>();
        String sql = "SELECT * FROM game_results WHERE username = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userName);
        while(results.next()) {
            GameResult gameResult = mapRowToGameResult(results);
            gameResultsListByUser.add(gameResult);
        }
        return gameResultsListByUser;
    }

    @Override
    public List<GameResult> findGameResultByNotCurrentUser(String userName) {
        List<GameResult> gameResultsListByUser = new ArrayList<>();
        String sql = "SELECT DISTINCT ON (game_id) game_id, user_id, username, game_name, cash_to_trade, investment_value \n" +
                "FROM game_results \n" +
                "WHERE game_name NOT IN (\n" +
                "\tSELECT game_name \n" +
                "\tFROM game_results \n" +
                "\tWHERE username = ?\n" +
                "\t);";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userName);
        while(results.next()) {
            GameResult gameResult = mapRowToGameResult(results);
            gameResultsListByUser.add(gameResult);
        }
        return gameResultsListByUser;
    }

    private GameResult mapRowToGameResult(SqlRowSet rs){
        GameResult gameResult = new GameResult();
        gameResult.setUserId(rs.getInt("user_id"));
        gameResult.setGameId(rs.getInt("game_id"));
        gameResult.setGameName(rs.getString("game_name"));
        gameResult.setUserName(rs.getString("username"));
        gameResult.setCashToTrade(rs.getBigDecimal("cash_to_trade"));
        gameResult.setInvestmentValue(rs.getBigDecimal("investment_value"));
        return gameResult;
    }
}
