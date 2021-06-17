package com.example.lottery.application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.ServiceLoader;

import com.example.lottery.service.business.StandardLotteryService;
import com.example.random.service.QualityLevel;
import com.example.random.service.RandomNumberService;
import com.example.random.service.ServiceQuality;

public class LotteryApplication {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		var props = new Properties();
		props.load(new FileInputStream(new File("src", "application.properties")));
		var level = QualityLevel.valueOf(props.get("random.number.service").toString());
		StandardLotteryService lotterySrv = new StandardLotteryService();
		ServiceLoader<RandomNumberService> randomNumberServices = ServiceLoader.load(RandomNumberService.class);
		for (var randomNumberService : randomNumberServices) {
			Class<? extends RandomNumberService> clazz = randomNumberService.getClass();
			if (clazz.isAnnotationPresent(ServiceQuality.class)) {
				var qualityLevel = clazz.getAnnotation(ServiceQuality.class).value();
				if (qualityLevel == level) {
					lotterySrv.setRandomNumberService(randomNumberService);
					System.err.println("Using " + randomNumberService.getClass().getName());
					System.err.println(lotterySrv.getLotteryNumbers(60, 6, 3));
				}
			}
		}
	}
}
