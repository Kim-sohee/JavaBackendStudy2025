<%@ page contentType="text/html; charset=UTF-8"%>
<%
	//클라이언트가 전송한 쿠키를 확인해보자.
	//웹 브라우저가 무조건 전송한 쿠키 정보를 보유한 객체인 Cookie를 이용하여,
	//브라우저가 전송한 정보를 출력해보자
	Cookie[] cookies = request.getCookies();
	for(Cookie cookie : cookies){
		out.print("쿠키명"+cookie.getName()+", 쿠키값"+cookie.getValue());
	}

%>