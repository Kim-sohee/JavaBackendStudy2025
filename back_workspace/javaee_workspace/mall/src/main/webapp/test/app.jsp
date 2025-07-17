<%@ page contentType="text/html; charset=UTF-8"%>
<%
	/*웹 요청 처리 흐름에서, 데이터를 저장할 수 있는 객체의 종류에는 다음과 같은 것들이 있음
	1) HttpServletRequest: 요청처리 끝날때까지
	2) HttpSession: 세션의 유지 시간까지
	3) ServletContext: 애플리케이션이 실행되는 동안 유지(톰켓 실행 시~톰켓 종료 시까지 살아남음)
	*/
	request.setAttribute("addr", "Inchen");
	
	session.setAttribute("name", "소희");
	
	application.setAttribute("nick", "sohee");
%>