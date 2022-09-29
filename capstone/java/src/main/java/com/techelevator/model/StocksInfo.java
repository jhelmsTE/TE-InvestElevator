package com.techelevator.model;

import com.techelevator.dao.StocksInfoDao;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@CrossOrigin
public class StocksInfo implements StocksInfoDao {

    private static final String API_BASE_URL = "https://api.polygon.io/v3/reference/tickers/";
    private static final String API_BASE_KEY = "?apiKey=J8Q9pZADexcYKvsxHJcBJKNPWCwoEh7g";

    private String tickerSymbol;

    public StocksInfo(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

@Override
@RequestMapping(path= "/{ticker}", method = RequestMethod.GET)
public StocksInfo getStockInfo(@PathVariable ("ticker") String tickerSymbol){

        return null;
}

    @Override
    public BigDecimal getStockPriceFromAPI(String url) {


        System.out.println("Hey now");

        return null;
    }




}





























