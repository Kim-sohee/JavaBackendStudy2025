package com.sinse.boardapp.comment.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sinse.boardapp.exception.NewsException;
import com.sinse.boardapp.model.News;
import com.sinse.boardapp.repository.NewsDAO;

public class UpdateServlet extends HttpServlet {
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
		
		News news = new News();
		news.setNews_id(Integer.parseInt(news_id));
		news.setTitle(title);
		news.setWriter(writer);
		news.setContent(content);
		
		out.print("<script>");
		try {
			newsDAO.update(news);
			out.print("alert('수정 성공');");
			out.print("location.href='/news/content.jsp?news_id="+news_id+"';");
		} catch (NewsException e) {
			e.printStackTrace();
			out.print("alert('"+e.getMessage()+"');");
			out.print("history.back();");
		}
		out.print("</script>");
		
	}
}
