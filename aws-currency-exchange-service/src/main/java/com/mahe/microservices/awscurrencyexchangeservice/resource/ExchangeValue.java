package com.mahe.microservices.awscurrencyexchangeservice.resource;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.persistence.Id;

@Entity
public class ExchangeValue {

	@Id
	private Long Id;
	
	@Column(name="currency_from")
	private String from;
	
	@Column(name="currency_to")
	private String to;
	
	private BigDecimal conversionMultiple;
	
	@Transient
	private String exchangeEnvInfo;

	public ExchangeValue() {

	}	
	public ExchangeValue(Long id, String from, String to, BigDecimal conversionMultiple) {
		super();
		Id = id;
		this.from = from;
		this.to = to;
		this.conversionMultiple = conversionMultiple;
	}
	public Long getId() {
		return Id;
	}
	public String getFrom() {
		return from;
	}
	public String getTo() {
		return to;
	}
	public BigDecimal getConversionMultiple() {
		return conversionMultiple;
	}
	public String getExchangeEnvInfo() {
		return exchangeEnvInfo;
	}
	public void setExchangeEnvInfo(String exchangeEnvInfo) {
		this.exchangeEnvInfo = exchangeEnvInfo;
	}	
	
}
