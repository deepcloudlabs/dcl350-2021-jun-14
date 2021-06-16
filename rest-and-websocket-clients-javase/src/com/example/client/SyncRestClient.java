package com.example.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

// Duration: 7976 ms.
public class SyncRestClient {
	private static final String BINANCE_REST_OVER_HTTP_URL = 
			"https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT";

	public static void main(String[] args) throws IOException, InterruptedException {
		var client = HttpClient.newHttpClient();
		var request = HttpRequest.newBuilder(URI.create(BINANCE_REST_OVER_HTTP_URL))
				.header("Accept", "application/json").build();
		var start = System.currentTimeMillis();
		for (var i = 0; i < 20; ++i) {
			var ticker = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
			System.err.println(ticker);
		}
		var stop = System.currentTimeMillis();
		System.err.println("Duration: " + (stop - start) + " ms.");
	}

}
