package com.techelevator.controller;


import com.techelevator.model.Stocks;
import com.techelevator.dao.*;
import com.techelevator.model.StockTickerNotFoundException;
import com.techelevator.model.StocksInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
//@PreAuthorize("isAuthenticated()")
//@RequestMapping("/stocks")
public class StocksController {

    private UserDao userDao;
    private GameDao gameDao;
    private GameResultDao gameResultDao;

    private StocksDao stocksDao;
    private StocksInfoDao stocksInfoDao;

    public StocksController(UserDao userDao, StocksDao stocksDao, StocksInfoDao stocksInfoDao) {
        this.userDao = userDao;
        this.stocksDao = stocksDao;
        this.stocksInfoDao = stocksInfoDao;

    }
@RequestMapping(value = "/leaderboard/{gameId}", method = RequestMethod.GET)
public void getLeaderboard(@PathVariable int gameId) {
        Stocks stocks = new Stocks();
        stocks.setGameId(gameId);
        stocksDao.displayLeaderboard(stocks);
}

    @RequestMapping(value = "/stocks/obj", method = RequestMethod.POST)
    public void createStocksObject(@RequestBody Stocks stocks){
        StocksInfo stocksInfo = new StocksInfo(stocks.getTicker());
        stocks.setStockPrice(stocksInfo.getStockPriceInfoFromAPI().getStockPrice());
        stocksDao.createNewStockTransaction(stocks);
    }

    
}

