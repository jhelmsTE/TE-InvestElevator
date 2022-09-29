package com.techelevator.controller;

import com.techelevator.dao.GameDao;
import com.techelevator.dao.GameResultDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Game;
import com.techelevator.model.GameNotFoundException;
import com.techelevator.model.GameResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class GameController {
    private UserDao userDao;
    private GameDao gameDao;
    private GameResultDao gameResultDao;

    public GameController(UserDao userDao, GameDao gameDao) {
        this.userDao = userDao;
        this.gameDao = gameDao;
        this.gameResultDao = gameResultDao;
    }

    @RequestMapping(value = "/viewAllGames", method = RequestMethod.GET)
    public List<Game> viewAllGames() throws GameNotFoundException{
        return gameDao.findAllGames();
    }

    @RequestMapping(value = "/createGame", method = RequestMethod.POST)
    public void create(@Valid @RequestBody Game newGame){
    gameDao.createGame(newGame.getUsername(), newGame.getStartDate(),
            newGame.getEndDate(), newGame.getGameName(), newGame.getGameResult());
    }

    @RequestMapping(value ="/createGameResult", method = RequestMethod.POST)
    public void create(@Valid @RequestBody GameResult newGameResult){
        gameResultDao.createGameResult(newGameResult.getUserId(), newGameResult.getGameId(), newGameResult.getUserName(),
                                       newGameResult.getCashToTrade(), newGameResult.getTotalAccountValue());
    }


    }
