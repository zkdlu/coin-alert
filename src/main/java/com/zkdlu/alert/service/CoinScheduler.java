package com.zkdlu.alert.service;

import com.zkdlu.alert.domain.Coin;
import com.zkdlu.alert.domain.CoinEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
@Getter
@RequiredArgsConstructor
@Service
public class CoinScheduler {
    private final RestTemplate restTemplate;
    private final ApplicationEventPublisher eventPublisher;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void getCoin() {
        var coins = restTemplate.getForObject("https://api.upbit.com/v1/candles/minutes/1?market=KRW-BTC&count=1",
                Coin[].class);

        if (coins.length > 0) {
            log.info("{}", coins[0].getMarket());

            eventPublisher.publishEvent(new CoinEvent(this, coins[0]));
        }
    }
}
