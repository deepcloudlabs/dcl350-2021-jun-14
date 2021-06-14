package com.example.lottery.application;

import java.util.List;

public interface LotteryApplication {
	List<Integer> draw(int max,int size);
	List<List<Integer>> draw(int max,int size,int column);
}
