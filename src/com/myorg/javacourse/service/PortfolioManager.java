package com.myorg.javacourse.service;

import java.util.Date;

import com.myorg.javacourse.model.Portfolio;
import com.myorg.javacourse.model.Stock;

// TODO: Auto-generated Javadoc
/**
 * The Class PortfolioManager with the details of all the stocks.
 */
public class PortfolioManager {

	/**
	 * Gets the portfolio.
	 *
	 * @return the portfolio
	 */
	public Portfolio getPortfolio() {

		// step 1: Define a portfolio’s instance

		Portfolio myPortfolio = new Portfolio();

		// step 2:Set portfolio name to “Exercise 7 portfolio”

		myPortfolio.setTitle("Exercise 07 portfolio");

		// step 3: Update balance to 10,000$
		myPortfolio.updateBalance(10000);

		// step 4:creating their stock instances first:

		Date date = new Date();
		date.setDate(15);
		date.setMonth(11);
		date.setYear(2014);

		Stock st1 = new Stock();
		st1.setSymbol("PIH");
		st1.setAsk((float) 10.0);
		st1.setBid((float) 8.5);
		st1.setDate(date);

		Stock st2 = new Stock();
		st2.setSymbol("AAL");
		st2.setAsk((float) 30.0);
		st2.setBid((float) 25.5);
		st2.setDate(date);

		Stock st3 = new Stock();
		st3.setSymbol("CAAS");
		st3.setAsk((float) 20.0);
		st3.setBid((float) 15.5);
		st3.setDate(date);

		myPortfolio.addStock(st1);
		myPortfolio.addStock(st2);
		myPortfolio.addStock(st3);

		// step 4: Buy the following stocks
		myPortfolio.buyStock("PIH", 20);
		myPortfolio.buyStock("AAL", 30);
		myPortfolio.buyStock("CAAS", 40);

		// step 5: Sell ​all stocks of symbol AAL
		myPortfolio.sellStock("AAL", -1);

		// step 6: Remove symbol CAAS
		myPortfolio.removeStock("CAAS");

		return myPortfolio;
	}
}
