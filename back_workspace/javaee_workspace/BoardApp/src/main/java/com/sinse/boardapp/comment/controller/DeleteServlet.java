package com.sinse.boardapp.comment.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sinse.boardapp.exception.NewsException;
import com.sinse.boardapp.repository.NewsDAO;


public class DeleteServlet extends HttpServlet{
	NewsDAO newsDAO = new NewsDAO();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글 인코딩
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		int news_id = Integer.parseInt(request.getParameter("news_id"));
		
		out.print("<script>");
		try {
			newsDAO.delete(news_id);
			out.print("alert('삭제 성공');");
			out.print("location.href='/news/list.jsp';");
		} catch (NewsException e) {
			e.printStackTrace();
			out.print("alert('"+e.getMessage()+"')");
			out.print("history.back()");
		}
		out.print("</script>");
	}
}
