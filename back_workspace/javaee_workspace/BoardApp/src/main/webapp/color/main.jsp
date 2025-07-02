<%@ page contentType="text/html; charset=UTF-8"%>
<%	//이 main.jsp가 서블릿으로 변경되었을때의 service() 메서드 영역
	String bg = request.getParameter("bg");
	out.print(bg);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	$(()=>{
		//버튼을 누르면 서버의 jsp에게 색상 변경을 요청한다.
		$("button").click(()=>{
			//select 박스의 값 얻기
			location.href = "/color/main.jsp?bg="+$("select").val();	//링크는 Get방식의 요청이다.
		});
	});
</script>
</head>
<body bgcolor="<%=bg %>">
	<select>
		<option <%if(bg.equals("red")){ %> selected <%}%> value="red">red</option>
		<option <%if(bg.equals("blue")){ %> selected <%}%> value="blue">blue</option>
		<option <%if(bg.equals("orange")){ %> selected <%}%> value="orange">orange</option>
		<option <%if(bg.equals("green")){ %> selected <%}%> value="green">green</option>
	</select>
	<button>배경 색 바꾸기</button>
</body>
</html>