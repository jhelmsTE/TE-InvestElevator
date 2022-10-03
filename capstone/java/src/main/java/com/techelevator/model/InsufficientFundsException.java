package com.techelevator.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.NOT_FOUND, reason = "Not enough funds to complete this transaction.")
public class InsufficientFundsException extends RuntimeException{
}