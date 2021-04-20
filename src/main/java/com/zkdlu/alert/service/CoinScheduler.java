package com.zkdlu.alert.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Getter
@Service
public class CoinScheduler {
    @Value("${upbit.access}")
    private String accessKey;
    @Value("${upbit.secret}")
    private String secretKey;

    
}
