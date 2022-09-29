package com.techelevator.dao;

import com.techelevator.model.Stocks;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcStocksDao implements StocksDao {
    private final JdbcTemplate jdbcTemplate;
    public JdbcStocksDao(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    @Override
    public List<Stocks> showUserStocksByGame(String username, int gameId){
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
   public Stocks sellStockByTicker(String username, int gameId, String ticker) {
    Stocks stocks = new Stocks();
        String deleteStock = "DELETE FROM stocks (username, game_id, ticker, company_name, buy_price) " +
                "VALUES (?, ?, ? (SELECT company_name FROM stocks where ticker = ?), " +
                "(SELECT buy_price FROM stocks WHERE ticker = ?));";

        return stocks;
}

    @Override
    public Stocks buyStockByTicker(String username, int gameId, String ticker) {
        Stocks stocks = new Stocks();
        String companyName = "SELECT company_name FROM stocks WHERE ticker = ?;";
        String buyPrice = "SELECT buy_price FROM stocks WHERE ticker = ?;";

        String insertStock = "INSERT INTO stocks (username, game_id, ticker, company_name, buy_price) " +
                "VALUES (companyName, buyPrice, ? (SELECT company_name FROM stocks where ticker = ?), " +
                "(SELECT buy_price FROM stocks WHERE ticker = ?));";

        return stocks;
    }

    private Stocks mapRowToStocks(SqlRowSet rs) {
        Stocks stocks = new Stocks();
        stocks.setGameId(rs.getInt("game_id"));
        stocks.setUsername(rs.getString("username"));
        stocks.setTicker(rs.getString("ticker"));
        stocks.setCompanyName(rs.getString("company_name"));
        stocks.setBuyPrice(rs.getBigDecimal("buy_price"));
        return stocks;
    }

}
