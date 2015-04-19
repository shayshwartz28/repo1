package com.myorg.javacourse;

import java.util.Date;

public class Stock {
	
	private String symbol;
	private float ask;
	private float bid;
	private Date date;
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public float getAsk() {
		return ask;
	}
	public void setAsk(float ask) {
		this.ask = ask;
	}
	public float getBid() {
		return bid;
	}
	public void setBid(float bid) {
		this.bid = bid;
	}
	public Date getDate() {
		return date;
	}
	
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getHtmlDescription() {
		Date date = getDate();
		return  " <b> Stock symbol </b>: " + getSymbol() + 
				" <b> ask </b>: " + getAsk() + 
				" <b> bid </b>: " + getBid() + 
				" <b> Date </b>: " + date.getMonth() + "/" + date.getDate() + "/" + date.getYear();
	}
}
