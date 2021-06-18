package com.example.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DemoBusinessService {
	@Autowired
	private BusinessService businessService;
	private ExecutorService es = Executors.newFixedThreadPool(20);
	@Scheduled(fixedRate = 30_000)
	public void demo() {
		System.err.println("Type of businessService: "+businessService.getClass().getName());
		for (var i=1; i<= 20; i++) {
			es.submit(() -> {
				var result = businessService.haveGun();
				System.err.println(String.format("%s produces %s.", Thread.currentThread().getName(), result));
			});
		}
	}
}
