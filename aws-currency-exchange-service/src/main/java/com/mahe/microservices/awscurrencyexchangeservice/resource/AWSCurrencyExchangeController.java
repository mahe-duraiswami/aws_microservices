package com.mahe.microservices.awscurrencyexchangeservice.resource;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.mahe.microservices.awscurrencyexchangeservice.util.ContainerMetadataService;

@RestController
public class AWSCurrencyExchangeController {
	
	private static final Logger log = LoggerFactory.getLogger(AWSCurrencyExchangeController.class);
	
	@Autowired
	private ExchangeValueRepository repository;

	@Autowired
	private ContainerMetadataService containerMetadataService;
	
	@GetMapping(path="/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from,@PathVariable String to,@RequestHeader Map<String,String> headers) {
		
		headers.forEach((key, value) -> {
			log.info(String.format("Header '%s' = %s", key, value));
		});
		
		ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);
		
		log.info("{} {} {}", from, to, exchangeValue);
		
		if (exchangeValue == null) {
			throw new RuntimeException("Unable to find data to convert " + from + " to " + to);
		}
		
		exchangeValue.setExchangeEnvInfo(containerMetadataService.retrieveContainerMetadataInfo());

		return exchangeValue;
	}

}
