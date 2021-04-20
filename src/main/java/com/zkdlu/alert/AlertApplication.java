package com.zkdlu.alert;

import com.zkdlu.alert.service.CoinScheduler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class AlertApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(AlertApplication.class, args);
	}

	@Autowired
	CoinScheduler coin;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("{}, {}", coin.getAccessKey(), coin.getSecretKey());
	}
}
