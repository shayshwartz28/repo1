package com.myorg.javacourse.model;

import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The Class Stock.
 */
public class Stock {
	
	/** The symbol. */
	private String symbol;
	
	/** The ask. */
	private float ask;
	
	/** The bid. */
	private float bid;
	
	/** The date. */
	private Date date;
	
	/** The recommendation. */
	private int recommendation;
	
	/** The stock quantity. */
	private int stockQuantity;
	
	/** The Constant BUY. */
	private final static int BUY = 0;
	
	/** The Constant SELL. */
	private final static int SELL = 1;
	
	/** The Constant REMOVE. */
	private final static int REMOVE = 2;
	
	/** The Constant HOLD. */
	private final static int HOLD = 3;
	
	
	/**
	 * Instantiates a new stock.
	 */
	public Stock() {
		super();
	}

	/**
	 * Instantiates a new stock.
	 * 
	 * @param other the other
	 */
	public Stock(Stock other) {
		super();
		setSymbol(other.getSymbol());
		setAsk(other.getAsk());
		setBid(other.getBid());
		setDate(other.getDate());
		setRecommendation(other.getRecommendation());
		setStockQuantity(other.getStockQuantity());
	}
	
	/**
	 * Gets the symbol.
	 *
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}
	
	/**
	 * Sets the symbol.
	 *
	 * @param symbol the new symbol
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	/**
	 * Gets the ask.
	 *
	 * @return the ask
	 */
	public float getAsk() {
		return ask;
	}
	
	/**
	 * Sets the ask.
	 *
	 * @param ask the new ask
	 */
	public void setAsk(float ask) {
		this.ask = ask;
	}
	
	/**
	 * Gets the bid.
	 *
	 * @return the bid
	 */
	public float getBid() {
		return bid;
	}
	
	/**
	 * Sets the bid.
	 *
	 * @param bid the new bid
	 */
	public void setBid(float bid) {
		this.bid = bid;
	}
	
	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	/**
	 * Gets the recommendation.
	 *
	 * @return the recommendation
	 */
	public int getRecommendation() {
		return recommendation;
	}

	/**
	 * Sets the recommendation.
	 *
	 * @param recommendation the recommendation to set
	 */
	public void setRecommendation(int recommendation) {
		this.recommendation = recommendation;
	}

	/**
	 * Gets the stock quantity.
	 *
	 * @return the stockQuantity
	 */
	public int getStockQuantity() {
		return stockQuantity;
	}

	/**
	 * Sets the stock quantity.
	 *
	 * @param stockQuantity the stockQuantity to set
	 */
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	/**
	 * Gets the html description.
	 *
	 * @return the html description
	 */
	public String getHtmlDescription() {
		Date date = getDate();
	
		return  " <b> Stock symbol </b>: " + getSymbol() + 
				" <b> ask </b>: " + getAsk() + 
				" <b> bid </b>: " + getBid() + 
				" <b> Date </b>: " + date.getMonth() + "/" + date.getDate() + "/" + date.getYear();
	}
}
