package com.example.lottery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.lottery.application.LotteryApplication;

@RestController
@RequestScope
@RequestMapping("/numbers")
@CrossOrigin
public class LotteryController {
	@Autowired
	private LotteryApplication lotteryApplication;
	
	// http://localhost:5100/lottery/api/v1/numbers?column=10
	@GetMapping
	public List<List<Integer>> draw(@RequestParam int column){
		return lotteryApplication.draw(60, 6, column);
	}
}
