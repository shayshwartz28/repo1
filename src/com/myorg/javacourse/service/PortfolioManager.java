package com.myorg.javacourse.service;

import java.util.Date;

import com.myorg.javacourse.model.Portfolio;
import com.myorg.javacourse.model.Stock;


/**
 * The Class PortfolioManager.
 */
public class PortfolioManager {

	/**
	 * Gets the portfolio.
	 *
	 * @return the portfolio
	 */
	public Portfolio getPortfolio() {

		Portfolio portfolio = new Portfolio();
		portfolio.setTitle("Portfolio #1");

		Date date = new Date();
		date.setDate(15);
		date.setMonth(11);
		date.setYear(2014);

		Stock st1 = new Stock();
		st1.setSymbol("PIH");
		st1.setAsk((float) 13.1);
		st1.setBid((float) 12.4);
		st1.setDate(date);

		Stock st2 = new Stock();
		st2.setSymbol("AAL");
		st2.setAsk((float) 5.78);
		st2.setBid((float) 5.5);
		st2.setDate(date);

		Stock st3 = new Stock();
		st3.setSymbol("CAAS");
		st3.setAsk((float) 32.2);
		st3.setBid((float) 31.5);
		st3.setDate(date);

		portfolio.addStock(st1);
		portfolio.addStock(st2);
		portfolio.addStock(st3);

		return portfolio;
	}
	
	

}
