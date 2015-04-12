package com.myorg.javacourse;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MathExercise03Servlet extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	private static final double RADIUS = 50.0;
	private static final double RADIANDS_30_DEGREES = Math.PI / 6;
	private static final double HYPOTENUSE = 50.0;
	private static final double BASE = 20.0;
	private static final double EXP = 13.0;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		double area = Math.PI * Math.pow(RADIUS, 2);
		double oppositeLength = Math.sin(RADIANDS_30_DEGREES) * HYPOTENUSE;
		
		String line1 = "calculation 1: Area of circle with radius " + RADIUS
				+ " is: " + area + " square-cm ";

		resp.setContentType("text/html");

		String line2 = "calculation 2: Length of opposite where angle B is 30 degrees and hypotenuse is "
				+ HYPOTENUSE + " cm is: " + oppositeLength + " cm ";

		String line3 = "calculation 3: Power of " + BASE + " with exp " + EXP
				+ " is: " + Math.pow(BASE, EXP);

		String resultStr = line1 + "<br>" + line2 + "<br>" + line3;
		resp.getWriter().println(resultStr);

	}
}
