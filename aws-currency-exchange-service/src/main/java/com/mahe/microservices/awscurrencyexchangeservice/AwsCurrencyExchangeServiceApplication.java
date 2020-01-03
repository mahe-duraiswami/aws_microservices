package com.mahe.microservices.awscurrencyexchangeservice;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mahe.microservices.awscurrencyexchangeservice.resource.ExchangeValue;
import com.mahe.microservices.awscurrencyexchangeservice.resource.ExchangeValueRepository;

@SpringBootApplication
public class AwsCurrencyExchangeServiceApplication  implements CommandLineRunner{
	
	@Autowired
	ExchangeValueRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(AwsCurrencyExchangeServiceApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {

		long count = repository.count();

		if (count == 0) {
			repository.save(new ExchangeValue(10001L, "USD", "INR", BigDecimal.valueOf(60)));
			repository.save(new ExchangeValue(10002L, "EUR", "INR", BigDecimal.valueOf(70)));
			repository.save(new ExchangeValue(10003L, "AUD", "INR", BigDecimal.valueOf(20)));
		}
	}

}
