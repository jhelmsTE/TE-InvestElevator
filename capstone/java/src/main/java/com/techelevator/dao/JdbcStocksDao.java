package com.techelevator.dao;

import com.techelevator.model.GameResult;
import com.techelevator.model.Stocks;
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


    @Override
    public List<Stocks> showUserStocksByGame(String username, int gameId) {
        List<Stocks> stocksList = new ArrayList<>();
        String sql = "SELECT ticker FROM stocks WHERE game_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
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
        BigDecimal cash = ( stocks.getStockPrice().multiply(BigDecimal.valueOf(stocks.getSharesSold())) ).subtract(
                stocks.getStockPrice().multiply(BigDecimal.valueOf(stocks.getSharesPurchased())) );

        String createStock = "BEGIN; INSERT INTO stocks (username, game_id, ticker, stock_price, " +
                "shares_purchased, shares_sold, shares_per_ticker, company_name)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?);" +
                "UPDATE game_results SET cash_to_trade = " +
                "cash_to_trade + ?, account_value = account_value - ? WHERE game_id = ? AND user_id = " +
                "(SELECT user_id FROM users WHERE username = ?); COMMIT";
        jdbcTemplate.update(createStock, stocks.getUsername(), stocks.getGameId(),
                stocks.getTicker(), stocks.getStockPrice(), stocks.getSharesPurchased(),
                stocks.getSharesSold(), stocks.getSharesPerTicker(),
                stocks.getCompanyName(), cash, cash, stocks.getUsername());

    }


    private Stocks mapRowToStocks(SqlRowSet rs) {
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

}
