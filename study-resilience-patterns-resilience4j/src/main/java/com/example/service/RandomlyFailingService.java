package com.example.service;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

@Service
public class RandomlyFailingService {

	public String haveFun() {
		var random = ThreadLocalRandom.current().nextDouble();
		if (random <= 0.9) {
			System.err.println("haveFun() returns 'success'.");
			return "success";
		} else if (random <= 0.99) {
			System.err.println("haveFun() returns 'failure'.");
			return "failure";
		} else {
			System.err.println("haveFun() throws IllegalStateException.");
			throw new IllegalStateException("This is not a legal state!");
		}
	}
}
