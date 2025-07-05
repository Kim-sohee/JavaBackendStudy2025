package com.sinse.boradapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sinse.boradapp.exception.NewsException;
import com.sinse.boradapp.model.News;
import com.sinse.boradapp.repository.NewsDAO;

public class UpdateServlet extends HttpServlet{
	NewsDAO newsDAO = new NewsDAO();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글 인코딩
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		//파라미터 받기
		String news_id = request.getParameter("news_id");
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		System.out.println(news_id);
		System.out.println(title);
		System.out.println(writer);
		System.out.println(content);
		
		//객체 담기
		News news = new News();
		news.setNews_id(Integer.parseInt(news_id));
		news.setTitle(title);
		news.setWriter(writer);
		news.setContent(content);
		
		out.print("<script>");
		try {
			newsDAO.update(news);
			out.print("alert('글 수정 완료');");
			out.print("location.href='/0703/news/content.jsp?news_id="+news_id+"';");
		} catch (NewsException e) {
			e.printStackTrace();
			out.print("alert('"+e.getMessage()+"');");
			out.print("history.back();");
		}
		out.print("</script>");
	}
}
