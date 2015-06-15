package com.myorg.javacourse.model;

import org.algo.model.PortfolioInterface;
import org.algo.model.StockInterface;

import com.myorg.javacourse.exception.BalanceExeception;
import com.myorg.javacourse.exception.InvalidStockQuantityException;
import com.myorg.javacourse.exception.PortfolioFullException;
import com.myorg.javacourse.exception.StockAlreadyExistsException;
import com.myorg.javacourse.exception.StockNotExistException;

// TODO: Auto-generated Javadoc
/**
 * The Class Portfolio.
 */
public class Portfolio implements PortfolioInterface {

	/**
	 * The Enum ALGO_RECOMMENDATION.
	 */
	public enum ALGO_RECOMMENDATION {

		/** The buy. */
		BUY,
		/** The sell. */
		SELL,
		/** The remove. */
		REMOVE,
		/** The hold. */
		HOLD
	};

	/** The title. */
	private String title = "Default Title";

	/** The max protfolio size. */
	private final int MAX_PORTFOLIO_SIZE;

	/** The stocks. */
	private StockInterface[] stocks;

	/** The portfolio size. */
	private int portfolioSize = 0;

	/** The balance. */
	private float balance;

	/**
	 * Instantiates a new portfolio.
	 */
	public Portfolio() {
		super();
		this.title = "Portfolio Title";
		MAX_PORTFOLIO_SIZE = 5;
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
	}

	/**
	 * Instantiates a new portfolio. Check what the protfolio size and make new
	 * one
	 * 
	 * @param title
	 *            the title
	 * @param mAX_PROTFOLIO_SIZE
	 *            the max_protfolio_size
	 */
	public Portfolio(String title, int mAX_PROTFOLIO_SIZE) {
		super();
		this.title = title;

		if (mAX_PROTFOLIO_SIZE <= 0) {
			mAX_PROTFOLIO_SIZE = 5;
		}

		MAX_PORTFOLIO_SIZE = mAX_PROTFOLIO_SIZE;
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
	}

	/**
	 * Instantiates a new portfolio. Take the details of the object and make new
	 * array (not by pointer)
	 * 
	 * @param other
	 *            the other
	 */

	public Portfolio(Portfolio other) {
		this(other.getTitle(), other.getMAX_PROTFOLIO_SIZE(),
				other.getStocks(), other.portfolioSize, other.getBalance());
	}

	/**
	 * Instantiates a new portfolio.
	 *
	 * @param title
	 *            the title
	 * @param mAX_PORTFOLIO_SIZE
	 *            the m a x_ portfoli o_ size
	 * @param stocks
	 *            the stocks
	 * @param portfolioSize
	 *            the portfolio size
	 * @param balance
	 *            the balance
	 */
	public Portfolio(String title, int mAX_PORTFOLIO_SIZE,
			StockInterface[] stocks, int portfolioSize, float balance) {
		super();
		this.title = title;
		MAX_PORTFOLIO_SIZE = mAX_PORTFOLIO_SIZE;
		this.stocks = stocks;
		this.portfolioSize = portfolioSize;
		this.balance = balance;
	}

	/**
	 * Instantiates a new portfolio.
	 *
	 * @param stockArray
	 *            the stock array
	 */
	public Portfolio(Stock[] stockArray) {
		if (stockArray == null || stockArray.length <= 4) {
			MAX_PORTFOLIO_SIZE = 5;
		} else {
			MAX_PORTFOLIO_SIZE = stockArray.length;
		}
		stocks = new Stock[MAX_PORTFOLIO_SIZE];

		for (portfolioSize = 0; portfolioSize < stockArray.length; portfolioSize++) {

			stocks[portfolioSize] = stockArray[portfolioSize];
		}

	}

	/**
	 * Adds the stock.
	 *
	 * @param stock
	 *            the stock
	 * @throws StockAlreadyExistsException
	 *             the stock already exists exception
	 * @throws PortfolioFullException
	 *             the portfolio full exception
	 */
	public void addStock(Stock stock) throws StockAlreadyExistsException,
			PortfolioFullException {

		if (stock != null && portfolioSize < stocks.length) {

			if (getStockIndex(stock.getSymbol()) != -1) {
				throw new StockAlreadyExistsException("Stock "
						+ stock.getSymbol() + " already exist in portfolio "
						+ title);
			}

			// if this is a new stock, set quantity 0 and add if to portfolio
			stock.setStockQuantity(0);
			stocks[portfolioSize] = (StockInterface) stock;
			portfolioSize++;
		} else {
			throw new PortfolioFullException("Portfolio " + title + " is full.");
		}
	}

	/**
	 * Gets the stock value.
	 *
	 * @return the stock value
	 */
	public float getStockValue() {
		float total = 0;
		for (int i = 0; i < getPortfolioSize(); i++) {
			total += ((Stock) stocks[i]).getStockQuantity()
					* stocks[i].getBid();
		}
		return total;
	}

	/**
	 * Gets the total value.
	 *
	 * @return the total value
	 */
	public float getTotalValue() {
		return getStockValue() + getBalance();
	}

	/**
	 * Gets the stock index.
	 *
	 * @param stockSymbol
	 *            the stock symbol
	 * @return the stock index
	 */
	private int getStockIndex(String stockSymbol) {

		for (int i = 0; i < portfolioSize; i++) {
			if (stocks[i].getSymbol().equals(stockSymbol)) {
				return i;
			}
		}
		return -1; // stock not found
	}

	/**
	 * Removes the stock.
	 *
	 * @param stockSymbol
	 *            the stock symbol
	 * @return true, if successful
	 * @throws StockNotExistException
	 *             the stock not exist exception
	 * @throws BalanceExeception
	 *             the balance exeception
	 * @throws InvalidStockQuantityException
	 *             the invalid stock quantity exception
	 */
	public void removeStock(String stockSymbol) throws StockNotExistException,
			BalanceExeception, InvalidStockQuantityException {

		int stockIndex = getStockIndex(stockSymbol);
		if (stockIndex == -1) {
			throw new StockNotExistException("Stock " + stockSymbol
					+ " not exist.");
		}
		sellStock(stockSymbol, -1);
		removeStockFromArray(stockIndex);
	}

	/**
	 * Sell stock.
	 *
	 * @param stockSymbol
	 *            the stock symbol
	 * @param quantity
	 *            the quantity
	 * @return true, if successful
	 * @throws StockNotExistException
	 *             the stock not exist exception
	 * @throws InvalidStockQuantityException
	 *             the invalid stock quantity exception
	 * @throws BalanceExeception
	 *             the balance exeception
	 */
	public void sellStock(String stockSymbol, int quantity)
			throws StockNotExistException, InvalidStockQuantityException,
			BalanceExeception {

		int stockIndex = getStockIndex(stockSymbol);
		float amount = 0;
		if (stockIndex == -1) {
			throw new StockNotExistException("Stock " + stockSymbol
					+ " not exist.");
		}
		Stock s = (Stock) stocks[stockIndex];
		if (s.getStockQuantity() < quantity || quantity < -1 || quantity == 0) {
			throw new InvalidStockQuantityException(
					"Invalid Stock Quantity: Cannot sell " + quantity
							+ " from stock " + stockSymbol + ".");
		}

		if (quantity == -1) { // sell all of this stock holdings
			amount = s.getStockQuantity() * s.getBid();
			updateBalance(amount);
			s.setStockQuantity(0);
		} else {
			amount = quantity * s.getBid();
			updateBalance(amount);
			s.setStockQuantity(s.getStockQuantity() - quantity);
		}
	}

	/**
	 * Buy stock.
	 *
	 * @param stockSymbol
	 *            the stock symbol
	 * @param quantity
	 *            the quantity
	 * @return true, if successful
	 * @throws BalanceExeception
	 *             the balance exeception
	 * @throws StockAlreadyExistsException
	 *             the stock already exists exception
	 * @throws PortfolioFullException
	 *             the portfolio full exception
	 * @throws StockNotExistException
	 *             the stock not exist exception
	 * @throws InvalidStockQuantityException
	 *             the invalid stock quantity exception
	 */
	public void buyStock(String stockSymbol, int quantity)
			throws BalanceExeception, StockAlreadyExistsException,
			PortfolioFullException, StockNotExistException,
			InvalidStockQuantityException {

		int stockIndex = getStockIndex(stockSymbol);
		float amount = 0;
		if (stockIndex == -1) {

			Stock s = new Stock(); //
			s.setSymbol(stockSymbol);
			addStock(s);
			stockIndex = getStockIndex(stockSymbol);
			if (stockIndex == -1)
				throw new StockNotExistException("Cannot buy. Stock "
						+ stockSymbol + " not exist.");
		}
		Stock s = (Stock) stocks[stockIndex];
		if (quantity < -1 || quantity == 0) {
			throw new InvalidStockQuantityException(
					"Invalid Stock Quantity: Cannot buy " + quantity
							+ " from stock " + stockSymbol + ".");
		}

		if (quantity == -1) { // Buy this stock with the total current balance
			int buyQuantity = (int) Math.floor(getBalance() / s.getAsk());
			amount = buyQuantity * s.getAsk();
			updateBalance(-amount);
			s.setStockQuantity(s.getStockQuantity() + buyQuantity);

		} else {
			amount = quantity * s.getAsk();
			updateBalance(-amount);
			s.setStockQuantity(s.getStockQuantity() + quantity);
		}
	}

	/**
	 * Removes cell by the stock index that have been given.
	 * 
	 * @param stockIndex
	 *            the stock index
	 */
	private void removeStockFromArray(int stockIndex) {
		if (portfolioSize > 0 && stockIndex >= 0 && stockIndex < portfolioSize) {
			for (int i = stockIndex; i < portfolioSize - 1; i++) {
				stocks[i] = stocks[i + 1];
				stocks[i + 1] = null;
			}
			portfolioSize--;
			if (portfolioSize == stockIndex) {
				stocks[stockIndex] = null;
			}
		}
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title
	 *            the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the max protfolio size.
	 *
	 * @return the mAX_PROTFOLIO_SIZE
	 */
	public int getMAX_PROTFOLIO_SIZE() {
		return MAX_PORTFOLIO_SIZE;
	}

	/**
	 * Gets the stocks.
	 *
	 * @return the stocks
	 */
	public StockInterface[] getStocks() {
		return stocks;
	}

	/**
	 * Gets the portfolio size.
	 *
	 * @return the portfolio size
	 */
	public int getPortfolioSize() {
		return portfolioSize;
	}

	/**
	 * Gets the html string.
	 *
	 * @return the html string
	 */
	public String getHtmlString() {
		String htmlStr = "<h1>" + getTitle() + "</h1>";

		htmlStr += "<br>" + "Total Portfolio Value: " + getTotalValue() + "$";
		htmlStr += "<br>" + "Total Stock Value: " + getStockValue() + "$";
		htmlStr += "<br>" + "Balance: " + getBalance() + "$";

		htmlStr += "<table style=\"width:50%\">";

		for (int i = 0; i < getPortfolioSize(); i++) {
			htmlStr += "<tr>" + ((Stock) getStocks()[i]).getHtmlDescription()
					+ " </tr>";
		}

		htmlStr += "</table>";

		htmlStr += "\n";
		return htmlStr;
	}

	/**
	 * Gets the balance.
	 *
	 * @return the balance
	 */
	public float getBalance() {
		return balance;
	}

	/**
	 * Sets the balance.
	 *
	 * @param balance
	 *            the balance to set
	 */
	private void setBalance(float balance) {
		this.balance = balance;
	}

	/**
	 * Update balance.
	 *
	 * @param amount
	 *            the amount
	 * @return true, if successful
	 * @throws BalanceExeception
	 *             the balance exeception
	 */
	public void updateBalance(float amount) throws BalanceExeception {

		float newBalance = getBalance() + amount;

		if (newBalance >= 0) {
			setBalance(newBalance);
		} else {
			throw new BalanceExeception("Balance cannot be negative");
		}
	}

	/**
	 * Find stock.
	 *
	 * @param symbol
	 *            the symbol
	 * @return the stock interface
	 */
	public StockInterface findStock(String symbol) {
		int stockIndex = getStockIndex(symbol);

		if (stockIndex == -1)
			return null;
		else
			return this.stocks[stockIndex];
	}
}
