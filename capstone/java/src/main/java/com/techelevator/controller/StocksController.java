package com.techelevator.controller;


import com.techelevator.model.*;
import com.techelevator.dao.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;


@RestController
@CrossOrigin
//@PreAuthorize("isAuthenticated()")
//@RequestMapping("/stocks")
public class StocksController {

    private UserDao userDao;
    private GameDao gameDao;
    private GameResultDao gameResultDao;
    private Leaderboard leaderboard;
    private Game game;

    private StocksDao stocksDao;
    private StocksInfoDao stocksInfoDao;

    public StocksController(UserDao userDao, StocksDao stocksDao, StocksInfoDao stocksInfoDao) {
        this.userDao = userDao;
        this.stocksDao = stocksDao;
        this.stocksInfoDao = stocksInfoDao;
    }

@RequestMapping(value = "/leaderboard/{gameId}", method = RequestMethod.GET)
public List<Leaderboard> getLeaderboardByGameId(@PathVariable int gameId) {

       return stocksDao.displayLeaderboard(gameId);
}

    @RequestMapping(value = "/stocks/obj", method = RequestMethod.POST)
    public void createStocksObject(@RequestBody Stocks stocks){
        StocksInfo stocksInfo = new StocksInfo(stocks.getTicker());
        stocks.setStockPrice(stocksInfo.getStockPriceInfoFromAPI().getStockPrice());
        stocksDao.createNewStockTransaction(stocks);

    }


    @RequestMapping(path= "/stocks/{id}", method = RequestMethod.GET)
    public List<UserShares> listOfStocks(@PathVariable int id, Principal principal){
        return stocksDao.displayShares(id, principal.getName());
    }


    
}

