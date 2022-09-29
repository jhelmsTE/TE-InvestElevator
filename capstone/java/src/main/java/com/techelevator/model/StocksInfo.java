package com.techelevator.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.techelevator.dao.StocksInfoDao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;


public class StocksInfo {
    //instance variables that match API JSON:
    private String ticker = "NFLX";
    private String name;






    private static final String API_BASE_URL = "https://api.polygon.io/v3/reference/tickers/";
    private static final String API_BASE_KEY = "?apiKey=J8Q9pZADexcYKvsxHJcBJKNPWCwoEh7g";



    public StocksInfo(String tickerSymbol) {
        this.ticker = tickerSymbol;
    }



    public BigDecimal getStockPriceFromAPI() {
        String qualifiedUrl = API_BASE_URL + ticker + API_BASE_KEY;
        RestTemplate restTemplate = new RestTemplate(); // Create a new client
        ResponseEntity response = restTemplate.getForEntity(
                qualifiedUrl,
                String.class); // Make GET request using client
        System.out.println(response.getBody()); // Your return data returned from .getBody()
        String[] basicStockInfo = response.getBody().toString().split(":");
        for (int i = 0; i< basicStockInfo.length ; i++){
            if(basicStockInfo [i].contains("ticker")){
               String tickerValue = basicStockInfo [i+1];
               String tickerName = tickerValue.substring(1, tickerValue.indexOf(",")-1);
                String nameValue = basicStockInfo [i+2];
                String stockName = nameValue.substring(1, nameValue.indexOf(",")-1);
                System.out.println(tickerName);
                System.out.println(stockName);
                break;
            }

        }

        //JsonNode node = new ObjectMapper().readTree(response.getBody());

        return null;
    }




}





























