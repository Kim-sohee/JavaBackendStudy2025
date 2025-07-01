package com.sinse.boardapp0701.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sinse.boardapp0701.exception.NoticeException;
import com.sinse.boardapp0701.model.Notice;
import com.sinse.boardapp0701.repository.NoticeDAO;

public class RegistServlet extends HttpServlet{
	NoticeDAO noticeDAO;
	
	public RegistServlet() {
		noticeDAO = new NoticeDAO();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//인코딩
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		Connection con;
		
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		try {
			noticeDAO.insert(notice);
			
			out.print("<script>");
			out.print("alert('등록성공');");
			out.print("location.href='/0701/notice/list.jsp';");	//목록 페이지를 재요청
			out.print("</script>");
		} catch (NoticeException e) {
			out.print("<script>");
			out.print("alert('"+e.getMessage()+"');");
			out.print("</script>");
			e.printStackTrace();
		}
	}
}
