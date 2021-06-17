package com.example.lottery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
// How to build the jar using maven:
// mvn clean install spring-boot:repackage

// How to run the spring boot application using maven:
// mvn clean install spring-boot:run

// How to over-write the parameters in application.properties:
//  i) java -Dserver.port=4200 -DlotteryMax=100 -DlotterySize=10 -jar lottery.jar

// ii) jar xvf lottery.jar BOOT-INF/classes/application.properties
//     move BOOT-INF\classes\application.properties .
//     java -cp . -jar lottery.jar  ==> now spring uses the extracted appication.properties file  

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
public class LotteryMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LotteryMicroserviceApplication.class, args);
	}

}
