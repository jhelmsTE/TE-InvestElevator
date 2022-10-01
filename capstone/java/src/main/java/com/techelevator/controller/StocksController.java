package com.techelevator.controller;

import com.techelevator.dao.*;
import com.techelevator.model.StockTickerNotFoundException;
import com.techelevator.model.StocksInfo;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
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

    @RequestMapping(value = "/buyStock/{ticker}", method = RequestMethod.POST)
public void buyStock(@PathVariable String ticker) throws StockTickerNotFoundException {

    }



}

