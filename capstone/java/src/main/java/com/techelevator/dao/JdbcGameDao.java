package com.techelevator.dao;

import com.techelevator.model.Game;
import com.techelevator.model.GameNotFoundException;
import com.techelevator.model.Stocks;
import com.techelevator.model.UserShares;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class JdbcGameDao implements GameDao{
    private StocksDao stocksDao;
    private JdbcTemplate jdbcTemplate;
    public JdbcGameDao(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}
    JdbcStocksDao sDao = new JdbcStocksDao(this.jdbcTemplate);
    private JdbcStocksDao jdbcStocksDao;
    @Override
    public List<Game> findAllGames() {
        List<Game> games = new ArrayList<>();
        String sql = "SELECT * FROM games;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()){
            Game game = mapRowToGame(results);
            games.add(game);
        }
        return games;
    }

    @Override
    public Game findGameById(int id) {
        String sql = "SELECT * FROM games WHERE game_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if(results.next()){
            return mapRowToGame(results);
        }else {
            throw new GameNotFoundException();
        }
    }

    @Override
    public boolean createGame(String username, Date startDate, Date endDate, String gameName, String gameResult) {

        String sql = "insert into games (organizer_id, start_date, " +
                "end_date, game_name, game_results) values (?,?,?,?,?)";

        return jdbcTemplate.update(sql, username, startDate, endDate, gameName, gameResult) == 1;
    }

    @Override
    public void updateGame(int id, Date startDate, Date endDate, String gameName) {
        String sql = "UPDATE games SET start_date = ?, end_date = ?, game_name = ? WHERE game_id = ? ";

        jdbcTemplate.update(sql, startDate, endDate, gameName, id);
    }
    @Override
    public Date changeCurrentDate(Game game, int id, Stocks stocks) {
        String sql = "UPDATE games SET start_date = start_date + INTERVAL '1 year' WHERE game_id = ?;";
        jdbcTemplate.update(sql, id);
        String sql2 = "SELECT start_date FROM games WHERE game_id = ?;";
        Date startDate = jdbcTemplate.queryForObject(sql2, Date.class, id);
       return startDate;
    }
    private Game mapRowToGame(SqlRowSet rs){
        Game game = new Game();
        game.setId(rs.getInt("game_id"));
        game.setStartDate(rs.getDate("start_date"));
        game.setEndDate(rs.getDate("end_date"));
        game.setGameName(rs.getString("game_name"));
        game.setGameResult(rs.getString("game_results"));
        game.setUsername(rs.getString("organizer_id"));
        return game;
    }
}
