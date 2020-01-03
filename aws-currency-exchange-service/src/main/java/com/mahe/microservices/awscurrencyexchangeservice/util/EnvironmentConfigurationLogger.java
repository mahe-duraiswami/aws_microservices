package com.mahe.microservices.awscurrencyexchangeservice.util;

import java.util.Arrays;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentConfigurationLogger {

	private static final Logger log = LoggerFactory.getLogger(EnvironmentConfigurationLogger.class);
	
	
	@SuppressWarnings("rawtypes")
	@EventListener
	public void handleContextRefresh(ContextRefreshedEvent event) {
		
		final Environment env = event.getApplicationContext().getEnvironment();
		
		log.info("*******Environment Details*********");
		
		log.info("Active profiles: {}", Arrays.toString(env.getActiveProfiles()));
		
		final MutablePropertySources sources = ((AbstractEnvironment) env).getPropertySources();
		
		StreamSupport.stream(sources.spliterator(), false).filter(ps -> ps instanceof EnumerablePropertySource)
				.map(ps -> ((EnumerablePropertySource) ps).getPropertyNames()).flatMap(Arrays::stream).distinct()
				.forEach(prop -> log.info("{}", prop));// environment.getProperty(prop)
		
		log.info("************************************");
		
		
	}
	
}
