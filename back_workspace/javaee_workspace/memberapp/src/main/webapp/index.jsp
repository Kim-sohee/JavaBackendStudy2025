<%@page import="com.sinse.memberapp.model.Member"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	//이 서블릿에 요청을 시도할 때, 웹 컨테이너에서는 세션 객체를 생성하면서, 동시에 세션 ID가 할당되어 진다.
	//그리고 이 세션 아이디는 톰켓이 보내는 응답정보에 쿠키 형태로 존재하게 된다.
	//단, 세션과 세션아이디는 매 요청마다 만들어지는게 아니라, 클라이언트가 요청 시 톰켓이 발급한 쿠키가 존재하지 않을 때는 새로 만든다.
	//또한 요청 후 일정 시간동안 아무런 재요청이 없을 때는 사용하지 않는 것으로 판단하여 새롭게 세션을 할당한다.
	//String id = session.getId();
	//out.print("지금 이 요청에 의해서 할당된 세션ID는 "+id);
	
	//만일 이 페이지의 요청자가 로그인에 성공한 사람이라면 세션에 member 라는 이름으로 객체가 담겨있음
	Member member = (Member)session.getAttribute("member");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%=member.getName() %>님 안녕하세요
</body>
</html>