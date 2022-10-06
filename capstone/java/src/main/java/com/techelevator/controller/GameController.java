package com.techelevator.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.dao.GameDao;
import com.techelevator.dao.GameResultDao;
import com.techelevator.dao.StocksDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.io.IOException;

import java.security.Principal;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class GameController {
    private UserDao userDao;
    private GameDao gameDao;
    private GameResultDao gameResultDao;
    private StocksDao stocksDao;


    public GameController(UserDao userDao, GameDao gameDao, GameResultDao gameResultDao, StocksDao stocksDao) {
        this.userDao = userDao;
        this.gameDao = gameDao;
        this.gameResultDao = gameResultDao;
        this.stocksDao = stocksDao;
    }

    @RequestMapping(value = "/viewAllGames", method = RequestMethod.GET)
    public List<Game> viewAllGames() throws GameNotFoundException {
        return gameDao.findAllGames();
    }

    @RequestMapping(value = "/createGame", method = RequestMethod.POST)
    public void create(@Valid @RequestBody Game newGame) {
        gameDao.createGame(newGame.getUsername(), newGame.getStartDate(),
                newGame.getEndDate(), newGame.getGameName(), newGame.getGameResult());
    }

    @RequestMapping(value = "/createGameResult", method = RequestMethod.POST)
    public void create(@RequestBody GameResult[] newGameResult) {
        gameResultDao.createGameResult(newGameResult);
    }

    @RequestMapping(path = "/viewGame/{id}", method = RequestMethod.GET)
    public Game get(@PathVariable int id) throws GameNotFoundException {
        return gameDao.findGameById(id);
    }

    @RequestMapping(path = "/viewGame/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable int id, @Valid @RequestBody Game updateGame) {
        gameDao.updateGame(id, updateGame.getStartDate(), updateGame.getEndDate(), updateGame.getGameName());
    }

    @RequestMapping(path = "/viewGameResults/{id}", method = RequestMethod.GET)
    public List<GameResult> getGameResults(@PathVariable int id) throws GameNotFoundException {
        return gameResultDao.findGameResultById(id);
    }



    @RequestMapping(path = "/endGame/{id}", method = RequestMethod.PUT)
    public void updateCurrentDate(@PathVariable int id) throws GameNotFoundException {
        stocksDao.endGame(id);
    }

    @RequestMapping(path = "/updatePrice/{id}", method = RequestMethod.PUT)
    public void updateCurrentPrices(@PathVariable int id) throws GameNotFoundException {
        stocksDao.endGame(id);
    }

    //findGameResultByUsername
    @RequestMapping(path = "/viewGameResultsByUsername", method = RequestMethod.GET)
    public List<GameResult> getGameResultsByUsername(Principal principal) throws GameNotFoundException {
        return gameResultDao.findGameResultByCurrentUser(principal.getName());
    }

    //findGameResultByNotCurrentUser
    @RequestMapping(path = "/viewGameResultsByNotCurrentUser", method = RequestMethod.GET)
    public List<GameResult> getGameResultsByNotCurrentUser(Principal principal) throws GameNotFoundException {
        return gameResultDao.findGameResultByNotCurrentUser(principal.getName());

    }

}

