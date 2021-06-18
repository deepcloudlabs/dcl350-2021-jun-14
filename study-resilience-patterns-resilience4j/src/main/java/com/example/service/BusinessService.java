package com.example.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class BusinessService {
	@Autowired
	private RandomlyFailingService randomlyFailingService;
	
	//@Retry(name="retry1",fallbackMethod = "retryFallbackMethod")
	//@RateLimiter(name="ratelimiter1",fallbackMethod = "rateLimiterFallbackMethod")
	//@Bulkhead(name="bulkhead1",type = Type.SEMAPHORE)
	@CircuitBreaker(name="circuitBreaker1")
	public String haveGun() {
		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
		}
		return randomlyFailingService.haveFun();
	}
	
	public String retryFallbackMethod(Exception e) {
		return "retry-fallback-success";
	}
	
	public String rateLimiterFallbackMethod(Exception e) {
		return "rateLimiter-fallback-success";
	}
	
}
