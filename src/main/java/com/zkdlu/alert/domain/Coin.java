package com.zkdlu.alert.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class Coin {
    private String market;
    private LocalDateTime candleDateTimeUtc;
    private LocalDateTime candleDateTimeKst;
    private double openingPrice;
    private double highPrice;
    private double lowPrice;
    private double tradePrice;
    private long timeStamp;
    private double candleAccTradePrice;
    private double candleAccTradeVolume;
    private long unit;
}
