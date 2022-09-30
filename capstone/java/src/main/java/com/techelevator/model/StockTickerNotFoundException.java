package com.techelevator.model;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.BAD_REQUEST, reason = "That Ticker Symbol Does Not Exist.")
public class StockTickerNotFoundException extends RuntimeException{
}
