package com.myorg.javacourse.model;

// TODO: Auto-generated Javadoc
/**
 * The Class Portfolio.
 */
public class Portfolio {

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
	private String title;

	/** The max protfolio size. */
	private final int MAX_PROTFOLIO_SIZE;

	/** The stocks. */
	private Stock[] stocks;

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
		MAX_PROTFOLIO_SIZE = 5;
		stocks = new Stock[MAX_PROTFOLIO_SIZE];
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

		MAX_PROTFOLIO_SIZE = mAX_PROTFOLIO_SIZE;
		stocks = new Stock[MAX_PROTFOLIO_SIZE];
	}

	/**
	 * Instantiates a new portfolio. Take the details of the object and make new
	 * array (not by pointer)
	 * 
	 * @param other
	 *            the other
	 */
	public Portfolio(Portfolio other) {
		this(other.getTitle(), other.getMAX_PROTFOLIO_SIZE());
		this.portfolioSize = other.getPortfolioSize();

		for (int i = 0; i < portfolioSize; i++) {
			stocks[i] = new Stock(other.getStocks()[i]);
		}
	}

	/**
	 * Adds the stock.
	 *
	 * @param stock
	 *            the stock
	 */
	public void addStock(Stock stock) {

		if (portfolioSize < stocks.length) {

			if (getStockIndex(stock.getSymbol()) != -1) {
				// stock already exist in array so
				// as requested, we ignore adding existing stocks to the
				// portfolio stock array
				return;
			}

			// if this is a new stock, set quantity 0 and add if to portfolio
			stock.setStockQuantity(0);
			stocks[portfolioSize] = stock;
			portfolioSize++;
		} else {
			System.err.println("Can't add new stock, portfolio can have only "
					+ portfolioSize + " stocks.");
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
			total += stocks[i].getStockQuantity() * stocks[i].getBid();
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
	 */
	public boolean removeStock(String stockSymbol) {

		int stockIndex = getStockIndex(stockSymbol);
		if (stockIndex == -1) {
			System.err.println("Stock:" + stockSymbol + " not found.");
			return false;
		} else {
			if (sellStock(stockSymbol, -1)) {
				removeStockFromArray(stockIndex);
			} else {
				return false;
			}
		}
		return true;
	}

	/**
	 * Sell stock.
	 *
	 * @param stockSymbol
	 *            the stock symbol
	 * @param quantity
	 *            the quantity
	 * @return true, if successful
	 */
	public boolean sellStock(String stockSymbol, int quantity) {

		int stockIndex = getStockIndex(stockSymbol);
		float amount = 0;
		if (stockIndex == -1) {
			System.err.println("Stock:" + stockSymbol + " not found.");
			return false;
		}
		Stock s = stocks[stockIndex];
		if (s.getStockQuantity() < quantity || quantity < -1 || quantity == 0) {
			System.err.println("Sell Stock: cant sell " + quantity
					+ " from stock.");
			return false;
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
		return true;
	}

	/**
	 * Buy stock.
	 *
	 * @param stockSymbol
	 *            the stock symbol
	 * @param quantity
	 *            the quantity
	 * @return true, if successful
	 */
	public boolean buyStock(String stockSymbol, int quantity) {

		int stockIndex = getStockIndex(stockSymbol);
		float amount = 0;
		if (stockIndex == -1) {

			Stock s = new Stock();
			s.setSymbol(stockSymbol);
			addStock(s);
			stockIndex = getStockIndex(stockSymbol);
			if (stockIndex == -1)
				return false;
		}
		Stock s = stocks[stockIndex];
		if (quantity < -1 || quantity == 0) {
			System.err.println("Buy Stock: cant Buy " + quantity
					+ " from stock.");
			return false;
		}

		if (quantity == -1) { // Buy this stock with the total current balance
			int buyQuantity = (int) Math.floor(getBalance() / s.getAsk());
			amount = buyQuantity * s.getAsk();
			updateBalance(-amount);
			s.setStockQuantity(s.getStockQuantity() + buyQuantity);

		} else {
			amount = quantity * s.getAsk();
			if (updateBalance(-amount)) {
				s.setStockQuantity(s.getStockQuantity() + quantity);
			} else {
				System.err
						.println("Buy Stock: not enough balance to complete purchase.");
				return false;
			}
		}
		return true;
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
			}
			portfolioSize--;
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
		return MAX_PROTFOLIO_SIZE;
	}

	/**
	 * Gets the stocks.
	 *
	 * @return the stocks
	 */
	public Stock[] getStocks() {
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
			htmlStr += "<tr>" + getStocks()[i].getHtmlDescription() + " </tr>";
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
	 */
	public boolean updateBalance(float amount) {

		float newBalance = getBalance() + amount;

		if (newBalance >= 0) {
			setBalance(newBalance);
			return true;
		} else {
			System.err.println("Cannot update to negative balance.");
			return false;
		}
	}

}
