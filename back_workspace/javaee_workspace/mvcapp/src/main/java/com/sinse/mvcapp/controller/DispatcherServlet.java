package com.sinse.mvcapp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sinse.mvcapp.color.model.BloodManager;
import com.sinse.mvcapp.color.model.ColorManager;

//모든 종류의 요청을 다 받을 수 있는 서블릿...
public class DispatcherServlet extends HttpServlet{
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*매 요청마다 1:1 대응되는 매핑을 피하기 위해 하나의 진입점으로 몰았으나,
		 * 진입점이 되는 클래스가 매 요청마다 1:1 대응되는 if 조건문이 작성되고 있다.*/
		if(request.getRequestURI().equals("/blood.do")) {	//클라이언트의 요청이 혈액형이면...
			//요청을 받는다.
			String blood = request.getParameter("blood");
			//요청을 분석한다.(생략)
			//일시킨다.
			BloodManager manager = new BloodManager();
			String result = manager.getAdvice(blood);
			
			HttpSession session  = request.getSession();	//이 요청에 의해 접근할 수 있는 세션 얻기
			session.setAttribute("msg", result);	 	//저장
			
			//알맞는 view 선택
			response.sendRedirect("/blood/result.jsp"); 	//클라이언트의 재접속 강제
			
		}else if(request.getRequestURI().equals("/color.do")) {
			String color = request.getParameter("color");
			ColorManager colorManager = new ColorManager();
			String result = colorManager.getAdvice(color);
			
			HttpSession session = request.getSession();
			session.setAttribute("msg", result);
			response.sendRedirect("/color/result.jsp");
		}
	}
}
