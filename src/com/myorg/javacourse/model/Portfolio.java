package com.myorg.javacourse.model;

import com.myorg.javacourse.Stock;

public class Portfolio {

	private String title;
	private final int MAX_PROTFOLIO_SIZE = 5;
	private Stock[] stocks;
	private int portfolioSize = 0;

	public Portfolio() {
		super();
		stocks = new Stock[MAX_PROTFOLIO_SIZE];
	}

	public void addStock(Stock stock) {
		if (portfolioSize < stocks.length) {
			stocks[portfolioSize] = stock;
			portfolioSize++;
		}

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Stock[] getStocks() {
		return stocks;
	}

	public int getPortfolioSize() {
		return portfolioSize;
	}

	public String getHtmlString() {
		String htmlStr = "<h1>" + getTitle() + "</h1>";

		for (int i = 0; i < getPortfolioSize(); i++) {
			htmlStr += "<br>" + getStocks()[i].getHtmlDescription();
		}

		return htmlStr;
	}

}
