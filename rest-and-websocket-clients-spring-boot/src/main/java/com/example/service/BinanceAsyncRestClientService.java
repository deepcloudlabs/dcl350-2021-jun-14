package com.example.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import com.example.dto.TickerDTO;

@Service
public class BinanceAsyncRestClientService {
    @Autowired
	private AsyncRestTemplate restTemplate;
    
    @Value("${binanceRestUrl}")
    private String binanceRestApiUrl;
    
	@Scheduled(fixedRate = 1_000)
	public void callBinance() {
		restTemplate.getForEntity(binanceRestApiUrl, TickerDTO.class)
		            .addCallback( response -> {
		            	System.err.println(Thread.currentThread().getName()+", Async: "+response.getBody());
		        		try { TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) { }
		            },error -> {
		            	System.err.println(error.getMessage());
		            }
		           );
	}
}
