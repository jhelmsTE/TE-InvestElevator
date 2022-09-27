package com.techelevator.controller;

import com.techelevator.dao.GameDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Game;
import com.techelevator.model.GameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class GameController {
    private UserDao userDao;
    private GameDao gameDao;

    public GameController(UserDao userDao, GameDao gameDao) {
        this.userDao = userDao;
        this.gameDao = gameDao;
    }

    @RequestMapping(value = "/viewAllGames", method = RequestMethod.GET)
    public List<Game> viewAllGames() throws GameNotFoundException{
        return gameDao.findAllGames();
    }
}
