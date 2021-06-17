package com.example.lottery.service;

import java.util.List;

public interface LotteryService {
	List<Integer> getLotteryNumbers(int max, int size);

	List<List<Integer>> getLotteryNumbers(int max, int size, int column);
}
