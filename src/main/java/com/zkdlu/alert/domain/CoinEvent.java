package com.zkdlu.alert.domain;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CoinEvent extends ApplicationEvent {
    private Coin coin;

    public CoinEvent(Object source, Coin coin) {
        super(source);
        this.coin = coin;
    }
}
