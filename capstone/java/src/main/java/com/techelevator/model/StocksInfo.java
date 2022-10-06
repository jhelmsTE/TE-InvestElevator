package com.techelevator.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.techelevator.dao.StocksInfoDao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class StocksInfo {


    //PT 1 of 3: API URL
    // a
    private static final String API_INFO_BASE_URL = "https://api.polygon.io/v3/reference/tickers/";
    // b
    private static final String API_PRICE_BASE_URL = "https://api.polygon.io/v2/aggs/ticker/";

    // PT 2 of 3: API URL - 'ticker' variable will change based on user input
    // a
    private String ticker;

   // a+ b
    
    private static final String API_RANGE_AND_DATE_BASE_URL = "/range/1/day/2022-05-04/2022-05-05?adjusted=true";
    private static final String API_RANGE_AND_DATE_BASE_URL2 = "/range/1/day/2022-08-04/2022-08-05?adjusted=true";

    //PT 3 of 3: API URL -
    private static final String API_BASE_INFO_KEY = "?apiKey=J8Q9pZADexcYKvsxHJcBJKNPWCwoEh7g";
    private static final String API_BASE_PRICE_KEY = "&apiKey=WTHBG8q_O3HFvbFDiuteBf7IaFZNtOkJ";

    public StocksInfo(String tickerSymbol) {
        this.ticker = tickerSymbol;
    }



//good example of how to grab ticker, company name from API endpoint above ^^
    public Stocks getStockTickerInfoFromAPI() {
        Stocks stockTickerInfo = new Stocks();
        String qualifiedUrl = API_INFO_BASE_URL + ticker + API_BASE_INFO_KEY;
        RestTemplate restTemplate = new RestTemplate(); // Create a new client
        ResponseEntity response = restTemplate.getForEntity(qualifiedUrl, String.class); // Make GET request using client
        System.out.println(response.getBody()); // Your return data returned from .getBody()
        String[] basicStockInfo = response.getBody().toString().split(":");
        for (int i = 0; i< basicStockInfo.length-1; i++){

            if(basicStockInfo [i].contains("ticker")){
            String tickerValue = basicStockInfo [i+1];
            String tickerName = tickerValue.substring(1, tickerValue.indexOf(",")-1);
            stockTickerInfo.setTicker(tickerName);
            String nameValue = basicStockInfo [i+2];
            String stockName = nameValue.substring(1, nameValue.indexOf(",")-1);
            stockTickerInfo.setCompanyName(stockName);

        }
        if(stockTickerInfo.getCompanyName() != null){
            break;
        }

        }

        return stockTickerInfo;
    }

    //now lets get the stock price from the API
    public Stocks getStockPriceInfoFromAPI(){
        Stocks stockPriceInfo = new Stocks();
        String qualifiedUrl = API_PRICE_BASE_URL + ticker + API_RANGE_AND_DATE_BASE_URL + API_BASE_PRICE_KEY;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity response = restTemplate.getForEntity(qualifiedUrl, String.class);
        System.out.println(response.getBody());//temporary to view return data for testing/validation
        String[] basicStockPriceInfo = response.getBody().toString().split(":");
        String priceValue = basicStockPriceInfo[17];
        BigDecimal closingPrice = new BigDecimal(priceValue.substring(0, priceValue.indexOf(",")));
        System.out.println(priceValue);
        System.out.println("--------------------------------------------------");
        System.out.println("$" + closingPrice);
        stockPriceInfo.setStockPrice(closingPrice);

        return stockPriceInfo;
    }


}





























