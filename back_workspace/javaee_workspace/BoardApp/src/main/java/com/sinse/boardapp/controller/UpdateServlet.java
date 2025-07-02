package com.sinse.boardapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sinse.boardapp.exception.NoticeException;
import com.sinse.boardapp.model.Notice;
import com.sinse.boardapp.repository.NoticeDAO;

//클라이언트 인 브라우저가 폼의 파라미터들을 포스트로 요청하고 있음
//따라서 doXXX형 중 doPost로 처리
public class UpdateServlet extends HttpServlet{
	NoticeDAO noticeDAO = new NoticeDAO();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글 인코딩
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");		//jsp의 page 지시영역과 동일
		
		PrintWriter out = response.getWriter();
		
		//파라미터 받기
		String notice_id = request.getParameter("notice_id");
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		notice.setNotice_id(Integer.parseInt(notice_id));
		
		out.print("<script>");
		try {
			noticeDAO.update(notice);	//수정 수행
			out.print("alert('수정성공');");
			out.print("location.href = '/notice/content.jsp?notice_id="+notice_id+"';");		//응답을 받은 브라우저로 하여금, 지정한 url로 재접속하라
			
		} catch (NoticeException e) {
			e.printStackTrace();
			out.print("alert('"+e.getMessage()+"');");
			out.print("history.back();");
		}
		out.print("</script>");
	}
}
