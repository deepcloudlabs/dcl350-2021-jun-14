package com.example.lottery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(value="strategy",havingValue = "feign")
public class LotteryConsumerFeignService {
	@Autowired
	private LotteryService lotteryService;
	
	@Scheduled(fixedRate = 1_000)
	public void callLotteryService() {
		System.err.println(lotteryService.getLotteryNumbers(4));
	}
}
