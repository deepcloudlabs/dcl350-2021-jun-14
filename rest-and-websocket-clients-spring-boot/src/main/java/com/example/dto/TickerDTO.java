package com.example.dto;

public class TickerDTO {
	private String symbol;
	private String price;

	public TickerDTO() {
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "TickerDTO [symbol=" + symbol + ", price=" + price + "]";
	}

}
