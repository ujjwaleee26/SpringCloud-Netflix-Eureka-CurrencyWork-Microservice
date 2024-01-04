package com.microservices.currencyexchangeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.currencyexchangeservice.bean.CurrencyExchange;
import com.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController 
{
	@Autowired
	private CurrencyExchangeRepository repository;
	
	@Autowired
	private Environment environment;
	
	
          @GetMapping("currency-exchange/from/{from}/to/{to}")
          public CurrencyExchange retrieveExchangeValue(@PathVariable String from,@PathVariable String to)
          {
        	 // CurrencyExchange currencyExchange = new CurrencyExchange(1000L,from,to,BigDecimal.valueOf(84));
        	  CurrencyExchange currencyExchange=repository.findByFromAndTo(from, to);
        	  currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
			return currencyExchange;
          }
          
}