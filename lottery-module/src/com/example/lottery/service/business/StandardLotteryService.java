package com.example.lottery.service.business;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.example.lottery.service.LotteryService;
import com.example.random.service.RandomNumberService;

public class StandardLotteryService implements LotteryService {

	private RandomNumberService randomNumberService;
	
	
	public void setRandomNumberService(RandomNumberService randomNumberService) {
		this.randomNumberService = randomNumberService;
	}

	@Override
	public List<Integer> getLotteryNumbers(int max, int size) {
		return IntStream.generate( () -> randomNumberService.generate(max))
				        .distinct()
				        .limit(size)
				        .sorted()
				        .boxed()
				        .collect(Collectors.toList());
	}

	@Override
	public List<List<Integer>> getLotteryNumbers(int max, int size, int column) {
		return IntStream.range(0, column)
				        .mapToObj( i -> getLotteryNumbers(max,size))
				        .collect(Collectors.toList());
	}

}
