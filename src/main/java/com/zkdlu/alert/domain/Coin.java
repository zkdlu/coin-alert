package com.zkdlu.alert.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
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
