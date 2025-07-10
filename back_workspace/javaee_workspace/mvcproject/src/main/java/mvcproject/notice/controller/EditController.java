package mvcproject.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvcproject.notice.domain.Notice;
import mvcproject.notice.repositroy.NoticeDAO;
import mvcproject.web.servlet.Controller;

public class EditController implements Controller{
	NoticeDAO noticeDAO = new NoticeDAO();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String notice_id = request.getParameter("notice_id");
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		Notice notice = new Notice();
		notice.setNotice_id(Integer.parseInt(notice_id));
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		//3단계: 일시키기
		noticeDAO.update(notice);
		
		//4단계: 값 저장
		request.setAttribute("notice", notice);
	}

	@Override
	public boolean isForward() {
		//원래 redirect가 맞지만, 매핑 파일 자체가 변수가 올 수 없으므로 포워딩으로 처리함
		return true;
	}

	@Override
	public String getViewName() {
		return "/notice/edit/view";
	}

}
