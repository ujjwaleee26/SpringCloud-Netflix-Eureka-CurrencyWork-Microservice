package com.microservices.currencyexchangeservice.controller;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController 
{
	private Logger logger=LoggerFactory.getLogger(CircuitBreakerController.class);
	
   @GetMapping("/sample-api")
   //@Retry(name="sample-api", fallbackMethod = "hardCoded")   //retry 3(default) times-->error
   //@CircuitBreaker(name="default", fallbackMethod = "hardCoded")
   @RateLimiter(name="default") //Xs-->Y calls to sample api
   //@Bulkhead(name="default")
   public String sampleApi()
   {
	   logger.info("Sample Api call received");
//	   ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);
//	   return forEntity.getBody();
	   return "Sample Api";
   }
   
   public String hardCoded(Exception ex)
   {
	   return "fallback";
   }
}