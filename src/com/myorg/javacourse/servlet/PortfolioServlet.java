package com.myorg.javacourse.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myorg.javacourse.model.Portfolio;
import com.myorg.javacourse.service.PortfolioManager;

// TODO: Auto-generated Javadoc
/**
 * The Class PortfolioServlet.
 */
public class PortfolioServlet extends HttpServlet {
    
	

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		
		resp.setContentType("text/html");
		PortfolioManager portfolioManager = new PortfolioManager();
		Portfolio portfolio1 = portfolioManager.getPortfolio();
		Portfolio portfolio2 = new Portfolio(portfolio1);
		portfolio2.setTitle("Portfolio #2");
		resp.getWriter().println(portfolio1.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());
		resp.getWriter().println("<br>");
		resp.getWriter().println("<br>removing first stock from " + portfolio1.getTitle()+ "</br>");
		portfolio1.removeStock(0);
		resp.getWriter().println(portfolio1.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());
		resp.getWriter().println("<br>");
		resp.getWriter().println("<br>changing last stocks BID of  " + portfolio2.getTitle() + "</br>");
		portfolio2.getStocks()[portfolio2.getPortfolioSize()-1].setBid((float)55.55);
		resp.getWriter().println(portfolio1.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());


	}
}
