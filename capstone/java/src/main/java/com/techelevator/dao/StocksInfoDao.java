package com.techelevator.dao;

import com.techelevator.model.Game;
import com.techelevator.model.Stocks;
import com.techelevator.model.StocksInfo;

import java.math.BigDecimal;

public interface StocksInfoDao {

StocksInfo getStockInfo(String ticker);

BigDecimal getStockPriceFromAPI(String url);

void updateStockPrices(Stocks stocks);
}
