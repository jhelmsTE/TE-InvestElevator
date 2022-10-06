package com.techelevator.dao;

import com.techelevator.model.Game;
import com.techelevator.model.Stocks;
import com.techelevator.model.StocksInfo;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class JdbcStocksInfoDao implements StocksInfoDao{
    private static final String API_BASE_URL = "https://api.polygon.io/v3/reference/tickers/";
    private static final String API_BASE_KEY = "?apiKey=J8Q9pZADexcYKvsxHJcBJKNPWCwoEh7g";

    @Override
    public StocksInfo getStockInfo(String ticker) {
        return null;
    }

    @Override
    public BigDecimal getStockPriceFromAPI(String url) {
        return null;
    }

    @Override
    public void updateStockPrices(Stocks stocks) {

        String sql = "UPDATE games SET";



    }


}
