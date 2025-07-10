package mvcproject.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvcproject.notice.domain.Notice;
import mvcproject.notice.repositroy.NoticeDAO;
import mvcproject.web.servlet.Controller;

public class RegistController implements Controller{
	NoticeDAO noticeDAO = new NoticeDAO();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//3단계: 일시키기
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		noticeDAO.insert(notice);
		
		//4단계: 저장하기 (생략)
	}
	
	@Override
	public boolean isForward() {
		return false;		//포워딩 안함, 브라우저로 하여금 새로 들어오게 한다.
	}

	@Override
	public String getViewName() {
		return "/notice/regist/view";		//목록 보여주기
	}

}
