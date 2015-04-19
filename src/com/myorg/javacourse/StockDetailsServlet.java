package com.myorg.javacourse;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StockDetailsServlet extends HttpServlet {
    
	

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		
		resp.setContentType("text/html");
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
		
		
		String line1 = st1.getHtmlDescription();
		String line2 = st2.getHtmlDescription();
		String line3 = st3.getHtmlDescription();

		String resultStr = line1 + "<br>" + line2 + "<br>" + line3;
		resp.getWriter().println(resultStr);

	}
}
