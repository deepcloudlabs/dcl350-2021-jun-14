package com.example.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.dto.Ticker;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/*

Calling binance rest api 19 times...
Calling binance rest api 19 times...✔
Ticker [symbol=BTCUSDT, price=37753.56000000]
Ticker [symbol=BCCBTC, price=0.07908100]
Ticker [symbol=ETHBTC, price=0.06170600]
Ticker [symbol=ETCETH, price=0.02329000]
Ticker [symbol=LTCBTC, price=0.00439000]
Ticker [symbol=EOSETH, price=0.00207200]
Ticker [symbol=BNTETH, price=0.00160100]
Ticker [symbol=EOSBTC, price=0.00012780]
Ticker [symbol=SUBETH, price=0.00012334]
Ticker [symbol=ENGBTC, price=0.00006200]
Ticker [symbol=SALTETH, price=0.00113800]
Ticker [symbol=SALTBTC, price=0.00004250]
Ticker [symbol=SNTETH, price=0.00002902]
Ticker [symbol=XVGETH, price=0.00001176]
Ticker [symbol=BNBBTC, price=0.00936800]
Ticker [symbol=DNTBTC, price=0.00000346]
Ticker [symbol=XVGBTC, price=0.00000072]
Ticker [symbol=NEOBTC, price=0.00125900]
Ticker [symbol=MTHBTC, price=0.00000064] 
 */ 
@Service
public class ReactiveBinanceClient {
	private static final String BINANCE_REST_OVER_HTTP_BASE_URL = "https://api.binance.com/api/v3";
	private static final List<String> SYMBOLS = List.of(
			"BTCUSDT","LTCBTC","ETHBTC","BNBBTC","NEOBTC","EOSETH",
			"SNTETH","BNTETH","BCCBTC","SALTBTC","SALTETH","XVGETH",
			"XVGBTC", "SUBETH","EOSBTC","MTHBTC","ETCETH","DNTBTC","ENGBTC"			
	);	
	private WebClient webClient = WebClient.builder().baseUrl(BINANCE_REST_OVER_HTTP_BASE_URL).build();
	private static final Comparator<Ticker> COMPARE_BY_PRICE_DESC =  
			(t1,t2) -> {
				var price1 = Double.parseDouble(t1.getPrice());
				var price2 = Double.parseDouble(t2.getPrice());
				return price2 < price1 ? -1 : +1;
			};
			
	@Scheduled(fixedRate = 5_000)
	public void callBinance() {
		System.err.println(String.format("Calling binance rest api %d times...",SYMBOLS.size()));
		Flux.fromIterable(SYMBOLS)
		    .parallel()
		    .runOn(Schedulers.boundedElastic())
		    .flatMap(this::getTicker)
		    .sorted(COMPARE_BY_PRICE_DESC) // global -> finite stream
		    //.ordered(COMPARE_BY_PRICE_DESC) // local -> infinite stream		    
		    .subscribe(System.err::println);		    
		System.err.println(String.format("Calling binance rest api %d times...✔",SYMBOLS.size()));
	}
	
	public Mono<Ticker> getTicker(String symbol){
		return webClient.get()
				        .uri(uriBuilder -> uriBuilder.path("/ticker/price")
				        		                     .queryParam("symbol", symbol)
				        		                     .build()
				       ).retrieve()
				        .bodyToMono(Ticker.class);
	}
}
