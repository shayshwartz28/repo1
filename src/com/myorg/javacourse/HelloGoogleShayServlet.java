package com.myorg.javacourse;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class HelloGoogleShayServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		int num1 = 4, num2 = 3, num3 = 7;
		String resultMsg = "<h1>Result of (" + num1 + " + " + num2 + ") * " + num3 + " = " + (num1 + num2)*num3 +"</h1>"; 
	
		resp.setContentType("text/html");
		resp.getWriter().println(resultMsg);
	}
}
