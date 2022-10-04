package com.techelevator.dao;

import com.techelevator.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcStocksDao implements StocksDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcStocksDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private GameResult gameResult;
    private Leaderboard leaderboard;


    @Override
    public List<UserShares> displayShares(int id, String username) {
        List<UserShares> userShares = new ArrayList<>();
        String sql = "SELECT game_id, username, ticker, shares_owned FROM user_shares_vw " +
                "WHERE game_id = ? AND username = ? ORDER BY ticker; ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id, username);
        while (results.next()) {
            userShares.add(mapRowToUserShares(results));
        }
        return userShares;
    }


    @Override
    public List<Stocks> showUserStocksByGame(int id) {
        List<Stocks> stocksList = new ArrayList<>();
        String sql = "SELECT username, game_id, ticker, stock_price, shares_purchased " +
                ", shares_sold, transaction_id, shares_per_ticker, company_name FROM stocks WHERE game_id = ?; ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

        while (results.next()) {
            Stocks stocks = mapRowToStocks(results);
            stocksList.add(stocks);
        }

        return stocksList;

    }

    @Override
    public Stocks getCompanyNameByTicker(String ticker) {
        Stocks stocks = new Stocks();
        ticker = ticker.toUpperCase();
        String sql = "SELECT company_name FROM stocks WHERE ticker = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            stocks = mapRowToStocks(results);

        }

        return stocks;
    }


    @Override
    @Transactional
    public void createNewStockTransaction(Stocks stocks) {
        BigDecimal cash = (stocks.getStockPrice().multiply(BigDecimal.valueOf(stocks.getSharesSold()))).subtract(
                stocks.getStockPrice().multiply(BigDecimal.valueOf(stocks.getSharesPurchased())));
        String Sql = "SELECT SUM(shares_owned) from user_shares_vw WHERE " +
                "game_id = ? AND username = ? AND ticker = ?;";
        Integer userShareCheck = jdbcTemplate.queryForObject(Sql, Integer.class,
                stocks.getGameId(), stocks.getUsername(), stocks.getTicker());
        if (userShareCheck == null) {
            userShareCheck = 0;
        }
        if (stocks.getSharesSold() > userShareCheck) {
            throw new InsufficientSharesException();
        }
        Sql = "SELECT cash_to_trade from game_results WHERE " +
                "game_id = ? AND username = ?;";

        BigDecimal userCashCheck = jdbcTemplate.queryForObject(Sql, BigDecimal.class, stocks.getGameId(), stocks.getUsername());
        if (BigDecimal.valueOf(stocks.getSharesPurchased()).multiply(stocks.getStockPrice()).compareTo(userCashCheck) > 0) {
            throw new InsufficientFundsException();
        } else {
            String createStock = "BEGIN; INSERT INTO stocks (username, game_id, ticker, stock_price, " +
                    "shares_purchased, shares_sold, company_name, shares_per_ticker) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, " +
                    "coalesce((SELECT shares_owned FROM user_shares_vw sq WHERE sq.game_id = ? AND sq.ticker = ? " +
                    "AND sq.username = ?),0) + (? - ?)); " +
                    "UPDATE game_results SET cash_to_trade = " +
                    "cash_to_trade + ?, total_account_value = total_account_value - ? WHERE game_id = ? " +
                    "AND user_id =  " +
                    "(SELECT user_id FROM users WHERE username = ?); COMMIT; ";
            jdbcTemplate.update(createStock, stocks.getUsername(), stocks.getGameId(),
                    stocks.getTicker(), stocks.getStockPrice(), stocks.getSharesPurchased(),
                    stocks.getSharesSold(), stocks.getCompanyName(), stocks.getGameId(), stocks.getTicker(), stocks.getUsername(),
                    stocks.getSharesPurchased(), stocks.getSharesSold(),
                    cash, cash, stocks.getGameId(), stocks.getUsername());

        }
    }

    @Override
    public List<Leaderboard> displayLeaderboard(int gameId) {
        // Once the timer hits "0" (i.e. the "end date and time" has arrived)
        // and a 'Game' has ended, then we want to call this method to 'displayLeaderboard'


      List<Leaderboard> leaderboard = new ArrayList<>();
        String Sql = "SELECT username, game_id, portfolio_value FROM portfolio_values_vw " +
                "WHERE game_id = ? " +
                "ORDER BY portfolio_value DESC;";

       SqlRowSet results = jdbcTemplate.queryForRowSet(Sql, gameId);
       while (results.next()) {
           leaderboard.add(mapRowToLeaderboard(results));
       }
       return leaderboard;


    }
    


        private Stocks mapRowToStocks (SqlRowSet rs){
            Stocks stocks = new Stocks();
            stocks.setUsername(rs.getString("username"));
            stocks.setGameId(rs.getInt("game_id"));
            stocks.setTicker(rs.getString("ticker"));
            stocks.setStockPrice(rs.getBigDecimal("stock_price"));
            stocks.setSharesPurchased(rs.getInt("shares_purchased"));
            stocks.setSharesSold(rs.getInt("shares_sold"));
            stocks.setTransactionId(rs.getInt("transaction_id"));
            stocks.setSharesPerTicker(rs.getInt("shares_per_ticker"));
            stocks.setCompanyName(rs.getString("company_name"));
            return stocks;
        }

    private Leaderboard mapRowToLeaderboard(SqlRowSet rs) {
        Leaderboard leaderboard = new Leaderboard();
        leaderboard.setUsername(rs.getString("username"));
        leaderboard.setGameId(rs.getInt("game_id"));
        leaderboard.setPortfolioValue(rs.getBigDecimal("portfolio_value"));
        return leaderboard;
    }



    private UserShares mapRowToUserShares(SqlRowSet rs) {
        UserShares userShares = new UserShares();
        userShares.setGameId(rs.getInt("game_id"));
        userShares.setUsername(rs.getString("username"));
        userShares.setTicker(rs.getString("ticker"));
        userShares.setSharesOwned(rs.getInt("shares_owned"));
        return userShares;
    }

        }

