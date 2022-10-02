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

//    @Override
//   public Stocks sellStockByTicker(String username, int gameId, String ticker) {
//    Stocks stocks = new Stocks();
//        String deleteStock = "DELETE FROM stocks (username, game_id, ticker, company_name, buy_price) " +
//                "VALUES (?, ?, ? (SELECT company_name FROM stocks where ticker = ?), " +
//                "(SELECT buy_price FROM stocks WHERE ticker = ?));";
//
//        return stocks;
//}

    @Override
    public void createStock(Stocks stocks) {
        String createStock = "INSERT INTO stocks (username, game_id, ticker, stock_price, " +
                "shares_purchased, shares_sold, shares_per_ticker, company_name)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(createStock, stocks.getUsername(), stocks.getGameId(),
                stocks.getTicker(), stocks.getStockPrice(), stocks.getSharesPurchased(),
                stocks.getSharesSold(), stocks.getSharesPerTicker(),
                stocks.getCompanyName());
    }

    @Override
    public Stocks buyStockByTicker(String username, int gameId, String ticker) {
        Stocks stocks = new Stocks();

        String insertStock = "INSERT INTO stocks (username, game_id, ticker, company_name, buy_price) " +
                "VALUES (companyName, buyPrice, ? (SELECT company_name FROM stocks where ticker = ?), " +
                "(SELECT buy_price FROM stocks WHERE ticker = ?));";
//        String companyName = "SELECT company_name FROM stocks WHERE ticker = ?;";
//        String buyPrice = "SELECT buy_price FROM stocks WHERE ticker = ?;";
        return stocks;
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
