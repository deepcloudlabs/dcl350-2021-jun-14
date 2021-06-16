package com.example.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

// Duration: 2387 ms.
public class AsyncRestClient {
	private static final String BINANCE_REST_OVER_HTTP_URL = 
			"https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT";
	private static final AtomicInteger counter = new AtomicInteger(0);
	public static void main(String[] args) throws IOException, InterruptedException {
		var client = HttpClient.newHttpClient();
		var request = HttpRequest.newBuilder(URI.create(BINANCE_REST_OVER_HTTP_URL))
				.header("Accept", "application/json").build();
		var start = System.currentTimeMillis();
		for (var i = 0; i < 20; ++i) {
			client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
			      .thenAccept( response -> {
			    	  System.err.println(Thread.currentThread().getName()+" : "+response.body());
			    	  var numberOfResponses = counter.incrementAndGet();
			    	  if (numberOfResponses>=20) {
			    		  var stop = System.currentTimeMillis();
			    		  System.err.println("Duration: " + (stop - start) + " ms.");			    		  
			    	  }
			      });
		}
		TimeUnit.SECONDS.sleep(10);
	}

}
