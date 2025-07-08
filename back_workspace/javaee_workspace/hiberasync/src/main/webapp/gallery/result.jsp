<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		//서블릿과는 달리 jsp이므로, session을 얻어올 때 내장 객체로 접근
		//session은 java.util.Map을 계승하였으므로, key-value 쌍으로 데이터를 관리한다.
		//따라서 session에 넣을 수 있는 데이터의 종류는 한계가 없는 객체이다.
		String img = (String)session.getAttribute("img");
		out.print(img);
	%>
	<img style="width:300px; height:300px" src="/data/<%=img%>">
</body>
</html>