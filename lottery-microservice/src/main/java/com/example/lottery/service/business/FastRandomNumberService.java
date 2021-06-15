package com.example.lottery.service.business;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

import com.example.lottery.service.RandomNumberService;

@Service
//@Primary
//@ServiceQuality(level = QualityLevel.FAST)
//@ConditionalOnProperty(name="randomNumberService", havingValue = "fast" )
public class FastRandomNumberService implements RandomNumberService {

	@Override
	public int generate(int min, int max) {
		System.err.println("FastRandomNumberService::generate");
		return ThreadLocalRandom.current().nextInt(min, max);
	}

}
