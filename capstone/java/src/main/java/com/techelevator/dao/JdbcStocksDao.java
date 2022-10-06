package com.techelevator.dao;

import com.techelevator.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void sellAllStocks(Stocks stocks) {
        String sql = "SELECT SUM(shares_owned) from user_shares_vw WHERE " +
                "game_id = ? AND username = ? AND ticker = ?;";
        Integer shares = jdbcTemplate.queryForObject(sql, Integer.class, stocks.getGameId(), stocks.getUsername(), stocks.getTicker());
        if (shares > 0) {
            stocks.setSharesSold(shares);
            createNewStockTransaction(stocks);
        }

    }



    private Map<String, BigDecimal> finalPricePerTicker(List<String> tickers) {
        Map<String, BigDecimal> pricePerTicker = new HashMap<>();

        for (String eachTicker : tickers) {
            StocksInfo stocksInfo = new StocksInfo(eachTicker);
            pricePerTicker.put(eachTicker, stocksInfo.getStockPriceInfoFromAPI().getStockPrice());
        }


        return pricePerTicker;

    }

    @Override
    public void endGame(int id) {
        List<String> tickerNames = new ArrayList<>();

        String Sql = "SELECT DISTINCT ticker FROM user_shares_vw " +
                "WHERE game_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(Sql, id);
        while (results.next()) {
            tickerNames.add(results.getString("ticker"));
        }
        Map<String, BigDecimal> pricePerTicker = finalPricePerTicker(tickerNames);

        Sql = "SELECT ticker, username, shares_owned FROM user_shares_vw " +
                "WHERE game_id = ? ORDER BY username;";
        results = jdbcTemplate.queryForRowSet(Sql, id);

        while (results.next()) {
            Stocks sellOrder = new Stocks();
            sellOrder.setTicker(results.getString("ticker"));
            sellOrder.setUsername(results.getString("username"));
            sellOrder.setSharesSold(results.getInt("shares_owned"));
            sellOrder.setStockPrice(pricePerTicker.get(results.getString("ticker")));
            sellOrder.setGameId(id);
            createNewStockTransaction(sellOrder);
        }


//        List<GameResult> finalResults = new ArrayList<>();
//         Sql = "SELECT game_id, username, game_name, cash_to_trade, total_account_value FROM game_results " +
//                 "WHERE game_id = ? ORDER BY cash_to_trade DESC;";
//         results = jdbcTemplate.queryForRowSet(Sql, id);
//        while (results.next()) {
//
//            finalResults.add(mapRowToGameResult(results));
//
//        }
//        System.out.println(finalResults);
//        return finalResults;
    }

    @Override
    public void updateStockPrices(int id) {
        List<String> tickerNames = new ArrayList<>();

        String Sql = "SELECT DISTINCT ticker FROM user_shares_vw " +
                "WHERE game_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(Sql, id);
        while (results.next()) {
            tickerNames.add(results.getString("ticker"));
        }
        Map<String, BigDecimal> pricePerTicker = finalPricePerTicker(tickerNames);

        Sql = "SELECT ticker, username, shares_owned FROM user_shares_vw " +
                "WHERE game_id = ? ORDER BY username;";
        results = jdbcTemplate.queryForRowSet(Sql, id);

        while (results.next()) {
            Stocks sellOrder = new Stocks();
            sellOrder.setTicker(results.getString("ticker"));
            sellOrder.setUsername(results.getString("username"));
            sellOrder.setSharesSold(results.getInt("shares_owned")); ;
            sellOrder.setStockPrice(pricePerTicker.get(results.getString("ticker")));
            sellOrder.setGameId(id);
            createNewStockTransaction(sellOrder);
        }
    }


    @Override
    @Transactional
    public void createNewStockTransaction(Stocks stocks) {
        BigDecimal cash = BigDecimal.ZERO;
        BigDecimal buyOrderValue = BigDecimal.ZERO;
        BigDecimal sellOrderValue = BigDecimal.ZERO;
        BigDecimal avgPricePerShare = BigDecimal.ZERO;

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

        if(stocks.getSharesSold() == 0) {
           buyOrderValue = stocks.getStockPrice().multiply(BigDecimal.valueOf(stocks.getSharesPurchased()));
            cash = buyOrderValue.negate();

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
                        "cash_to_trade + ?, investment_value = investment_value + ? WHERE game_id = ? " +
                        "AND user_id =  " +
                        "(SELECT user_id FROM users WHERE username = ?); COMMIT; ";
                jdbcTemplate.update(createStock, stocks.getUsername(),
                        stocks.getGameId(),
                        stocks.getTicker(),
                        stocks.getStockPrice(),
                        stocks.getSharesPurchased(),
                        stocks.getSharesSold(),
                        stocks.getCompanyName(),

                        stocks.getGameId(),

                        stocks.getTicker(),

                        stocks.getUsername(),

                        stocks.getSharesPurchased(),
                        stocks.getSharesSold(),

                        cash,
                        buyOrderValue,
                        stocks.getGameId(),
                        stocks.getUsername());

            }




        }else {
         String latestInvestmentValue = "SELECT investment_value FROM game_results WHERE game_id = ? AND username = ?; ";
         BigDecimal totalInvestmentValue = jdbcTemplate.queryForObject(latestInvestmentValue, BigDecimal.class, stocks.getGameId(), stocks.getUsername());

            sellOrderValue = stocks.getStockPrice().multiply(BigDecimal.valueOf(stocks.getSharesSold()));

            cash = sellOrderValue;

           String sql =  "SELECT  stock_price from stocks where username = ? AND ticker = ? AND game_id = ? ORDER BY transaction_id DESC  limit 1; ";
           BigDecimal latestPrice = jdbcTemplate.queryForObject(sql, BigDecimal.class, stocks.getUsername(), stocks.getTicker(), stocks.getGameId());

           if(sellOrderValue != latestPrice){
              totalInvestmentValue =  totalInvestmentValue.subtract(latestPrice);
              totalInvestmentValue =  totalInvestmentValue.add(sellOrderValue);

               String updatePrice = "UPDATE game_results SET investment_value = ? WHERE username = ? and game_id = ?; ";
               jdbcTemplate.update(updatePrice, totalInvestmentValue, stocks.getUsername(), stocks.getGameId());

           }


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
                        "cash_to_trade + ?, investment_value = investment_value - ? WHERE game_id = ? " +
                        "AND user_id =  " +
                        "(SELECT user_id FROM users WHERE username = ?); COMMIT; ";
                jdbcTemplate.update(createStock, stocks.getUsername(),
                        stocks.getGameId(),
                        stocks.getTicker(),
                        stocks.getStockPrice(),
                        stocks.getSharesPurchased(),
                        stocks.getSharesSold(),
                        stocks.getCompanyName(),

                        stocks.getGameId(),

                        stocks.getTicker(),

                        stocks.getUsername(),

                        stocks.getSharesPurchased(),
                        stocks.getSharesSold(),

                        cash,
                        sellOrderValue,
                        stocks.getGameId(),
                        stocks.getUsername());

            }



        }

    }


    @Override
    public List<Leaderboard> displayLeaderboard(int gameId) {

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


