package com.example.lottery.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@ConditionalOnProperty(value="strategy",havingValue = "hand-coded")
public class LotteryConsumerService {
	private static final String URL = "http://%s:%d/lottery/api/v1/numbers?column=4";
	private static final AtomicInteger counter = new AtomicInteger(0);

	@Autowired
	private DiscoveryClient discoveryClient;
	private List<ServiceInstance> instances;


	@PostConstruct
	public void getInstanceListFromEurekaServer() {
		instances = discoveryClient.getInstances("lottery"); // spring.application.name
	}

	@Scheduled(fixedRate = 1_000)
	public void callLotteryService() {
		var rt = new RestTemplate();
		var index = counter .getAndIncrement() % instances.size();
		var instance = instances.get(index);
		var host = instance.getHost();
		var port = instance.getPort();
		var response = rt.getForEntity(String.format(URL,host,port), String.class).getBody();
		System.err.println(response);
	}
}
