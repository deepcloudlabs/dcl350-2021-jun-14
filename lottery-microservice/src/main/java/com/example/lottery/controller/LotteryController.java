package com.example.lottery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.lottery.application.LotteryApplication;

@RestController
@RefreshScope
@RequestMapping("/numbers")
@CrossOrigin
public class LotteryController {
	@Autowired
	private LotteryApplication lotteryApplication;
	@Value("${lotteryMax}")
	private int lotteryMax;
	@Value("${lotterySize}")
	private int lotterySize;
	@Value("${server.port}")
	private int port;
	
	// http://localhost:5100/lottery/api/v1/numbers?column=10
	@GetMapping
	public List<List<Integer>> draw(@RequestParam int column){
		System.err.println("New request has arrived for port "+port+"!");
		return lotteryApplication.draw(lotteryMax, lotterySize, column);
	}
}
