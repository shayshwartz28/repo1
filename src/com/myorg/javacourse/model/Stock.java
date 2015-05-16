package com.myorg.javacourse.model;

import java.util.Date;

import com.myorg.javacourse.model.Portfolio.ALGO_RECOMMENDATION;

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
	private ALGO_RECOMMENDATION recommendation;

	/** The stock quantity. */
	private int stockQuantity;

	/**
	 * Instantiates a new stock.
	 */
	public Stock() {
		super();
	}

	/**
	 * Instantiates a new stock.
	 * 
	 * @param other
	 *            the other
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
	 * @param symbol
	 *            the new symbol
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
	 * @param ask
	 *            the new ask
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
	 * @param bid
	 *            the new bid
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
	 * @param date
	 *            the new date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Gets the recommendation.
	 *
	 * @return the recommendation
	 */
	public ALGO_RECOMMENDATION getRecommendation() {
		return recommendation;
	}

	/**
	 * Sets the recommendation.
	 *
	 * @param recommendation
	 *            the recommendation to set
	 */
	public void setRecommendation(ALGO_RECOMMENDATION recommendation) {
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
	 * @param stockQuantity
	 *            the stockQuantity to set
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

		return "<td> <b> Stock symbol </b>: " + getSymbol()
				+ "</td><td> <b> ask </b>: " + getAsk()
				+ " $</td><td><b> bid </b>: " + getBid()
				+ " $</td><td> <b> Quantity </b>: " + getStockQuantity()
				+ "</td><td> <b> Date </b>: " + (date.getMonth() + 1) + "/"
				+ date.getDate() + "/" + date.getYear() + "</td>";
	}
}
