<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	$(()=>{
		$("button").click(()=>{
			//폼의 데이터를 서버로 전송
			/* $("form").attr("method", "post");
			$("form").attr("action", "/upload/regist");
			$("form").attr("enctype", "multipart/form-data");	//텍스트 뿐 아니라 바이너리 파일로 폼 전송= 복합적 형식 선언 */
			
			//위의 html DOM 속성을 일일이 명시하기 보다는, json을 이용하여 코드를 줄이기
			$("form").attr({
				"method":"post",
				"action":"/upload/regist",
				"enctype": "multipart/form-data"
			});
			$("form").submit();	//전송
		});
	});
</script>
</head>
<body>
	
	<pre>
		<form>
			<input type="text" name="title"><br>	<!-- 텍스트 데이터 -->
			<input type="file" name="photo"><br> 	<!-- 바이너리 데이터 -->
			<button type="button">업로드</button>
		</form>
	</pre>
</body>
</html>