package com.techelevator.dao;

import com.techelevator.model.StocksInfo;

import java.math.BigDecimal;

public interface StocksInfoDao {

StocksInfo getStockInfo(String ticker);

    BigDecimal getStockPriceFromAPI(String url);
}
