package com.example.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.net.http.WebSocket.Listener;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

public class WebsocketClient {
	private static final String BINANCE_REST_OVER_WS_URL = 
			"wss://stream.binance.com:9443/ws/btcusdt@trade";
	public static void main(String[] args) throws IOException, InterruptedException {
		var listener = new BinanceWebSocketListener();
		HttpClient.newHttpClient()
	               .newWebSocketBuilder()
	               .buildAsync(URI.create(BINANCE_REST_OVER_WS_URL), listener );
		TimeUnit.SECONDS.sleep(20);
	}

}

class BinanceWebSocketListener implements Listener {

	@Override
	public void onOpen(WebSocket webSocket) {
		System.err.println("Connected to the binance server!");
		webSocket.request(1);
	}

	@Override
	public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
		System.err.println(data);
		return Listener.super.onText(webSocket, data, last);
	}

	@Override
	public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
		System.err.println("Disconnected from the binance server!");
		return Listener.super.onClose(webSocket, statusCode, reason);
	}

	@Override
	public void onError(WebSocket webSocket, Throwable error) {
		System.err.println("An error has occurred:"+error.getMessage());
	}
	
}