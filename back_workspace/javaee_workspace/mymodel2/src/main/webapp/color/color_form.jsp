<%@page import="mymodel2.model.color.ColorService"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	//서블릿의 서비스 메서드 영역
	//서비스 메서드 영역이 끝나면 response 객체에 저장(print.out 형태로..) 그리고 고양이(톰켓)가 그걸 읽음
	
	//결과 출력
	String msg = (String)request.getAttribute("msg");
	out.print(msg);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="form1">
		<select name="color">
			<option value="">색상 선택</option>
			<option value="red">RED</option>
			<option value="blue">BLUE</option>
			<option value="yellow">YELLOW</option>
			<option value="green">GREEN</option>
			<option value="black">BLACK</option>
		</select>
		<button type="button" onClick="getAdvice()">판단요청</button>
	</form>
	
	<script type="text/javascript">
		function getAdvice(){
			let form1 = document.getElementById("form1");
			form1.action="/color/advice";		//왜 http://localhost:8888이 붙지 않을까? => 브라우저가 알고 있기 때문!
			form1.method="post";
			form1.submit();
		}
	</script>
</body>
</html>