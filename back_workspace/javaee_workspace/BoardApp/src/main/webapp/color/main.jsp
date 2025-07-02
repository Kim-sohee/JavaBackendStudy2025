<%@ page contentType="text/html; charset=UTF-8"%>
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
			location.href = "/color/main.jsp";	//링크는 Get방식의 요청이다.
		});
	});
</script>
</head>
<body bgcolor="pink">
	<select>
		<option>red</option>
		<option>blue</option>
		<option>orange</option>
		<option>green</option>
	</select>
	<button>배경 색 바꾸기</button>
</body>
</html>