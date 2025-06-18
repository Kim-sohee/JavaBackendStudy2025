package com.sinse.web0618;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyServlet extends HttpServlet{
	//웹브라우저가 Get 방식으로 요청을 시도할 때 동작하는 메서드인 doGet 재정의
	@Override
	protected void doGet(HttpServletRequest requset, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.print("My Site's build is successful!");
	}
}
