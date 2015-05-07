package com.myorg.javacourse.model;


// TODO: Auto-generated Javadoc
/**
 * The Class Portfolio.
 */
public class Portfolio {

	/** The title. */
	private String title;
	
	/** The max protfolio size. */
	private final int MAX_PROTFOLIO_SIZE;
	
	/** The stocks. */
	private Stock[] stocks;
	
	/** The portfolio size. */
	private int portfolioSize = 0;

	
	

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
	 * Instantiates a new portfolio.
	 *
	 * @param title the title
	 * @param mAX_PROTFOLIO_SIZE the m a x_ protfoli o_ size
	 */
	public Portfolio(String title, int mAX_PROTFOLIO_SIZE) {
		super();
		this.title = title;
		
		if(mAX_PROTFOLIO_SIZE <= 0)
		{
			mAX_PROTFOLIO_SIZE = 5;
		}
			
		MAX_PROTFOLIO_SIZE = mAX_PROTFOLIO_SIZE;
		stocks = new Stock[MAX_PROTFOLIO_SIZE];
	}
	
	/**
	 * Instantiates a new portfolio.
	 *
	 * @param other the other
	 */
	public Portfolio(Portfolio other) {	
        this(other.getTitle(),other.getMAX_PROTFOLIO_SIZE());
        this.portfolioSize = other.getPortfolioSize();
        
        for (int i = 0; i < portfolioSize; i++) {
			stocks[i] = new Stock(other.getStocks()[i]);
		}
        
	}

	/**
	 * Adds the stock.
	 *
	 * @param stock the stock
	 */
	public void addStock(Stock stock) {
		if (portfolioSize < stocks.length) {
			stocks[portfolioSize] = stock;
			portfolioSize++;
		}
	}

	/**
	 * Removes the stock.
	 *
	 * @param stockIndex the stock index
	 */
	public void removeStock(int stockIndex) {
		if (portfolioSize > 0 && stockIndex >= 0 && stockIndex < portfolioSize ) {
			for (int i = stockIndex; i < portfolioSize-1; i++) {
				stocks[i] = stocks[i+1];
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
	 * @param title the new title
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

		for (int i = 0; i < getPortfolioSize(); i++) {
			htmlStr += "<br>" + getStocks()[i].getHtmlDescription();
		}
        htmlStr += "\n";
		return htmlStr;
	}

}
