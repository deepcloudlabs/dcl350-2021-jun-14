package com.example.lottery.application.business;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lottery.application.LotteryApplication;
import com.example.lottery.service.RandomNumberService;

@Service
public class StandardLotteryApplication implements LotteryApplication {
	@Autowired
	//@ServiceQuality(level=QualityLevel.FAST)
	private List<RandomNumberService> randomNumberServices;
	// private Map<String,RandomNumberService> randomNumberServices;
	private AtomicInteger counter = new AtomicInteger(0);
	
	@PostConstruct
	public void init() {
	    randomNumberServices.forEach(service -> System.err.println(service.getClass().getName()));  	
	}
	
	@Override
	public List<Integer> draw(int max, int size) {
		// Selection strategy: Round-Robin
		var index = counter.getAndIncrement() % randomNumberServices.size();
		var randomNumberService = randomNumberServices.get(index );
		// Use the selected implementation
		return IntStream.generate( () -> randomNumberService.generate(1,max))
				        .distinct()
				        .limit(size)
				        .sorted()
				        .boxed()
				        .collect(Collectors.toList());
	}

	@Override
	public List<List<Integer>> draw(int max, int size, int column) {
		return IntStream.range(0, column)
				        .mapToObj( i -> draw(max,size))
				        .collect(Collectors.toList());
	}

}
