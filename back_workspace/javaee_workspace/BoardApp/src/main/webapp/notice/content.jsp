<%@ page contentType="text/html; charset=UTF-8"%>
<%
	//요청 객체로부터 파라미터 뽑아내기
	//이 스크립틀릿 영역은 jsp가 서블릿으로 변경될 때, service() 메서드 영역이므로 이미 service() 메서드의 매개변수로
	//요청 객체와 응답객체를 넘겨받은 상태...service(HttpServletRequest request, HttpServletResponse response)
	String notice_id = request.getParameter("notice_id");
	String sql = "select * from notice where notice_id="+notice_id;
	out.print(sql);
%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta charset="UTF-8">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	
	<!-- include summernote css/js -->
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.js"></script>
	<style>
		body {font-family: Arial, Helvetica, sans-serif;}
		* {box-sizing: border-box;}
		
		input[type=text], select, textarea {
		  width: 100%;
		  padding: 12px;
		  border: 1px solid #ccc;
		  border-radius: 4px;
		  box-sizing: border-box;
		  margin-top: 6px;
		  margin-bottom: 16px;
		  resize: vertical;
		}
		
		input[type=button] {
		  background-color: #04AA6D;
		  color: white;
		  padding: 12px 20px;
		  border: none;
		  border-radius: 4px;
		  cursor: pointer;
		}
		
		input[type=button]:hover {
		  background-color: #45a049;
		}
		
		.container {
		  border-radius: 5px;
		  background-color: #f2f2f2;
		  padding: 20px;
		}
	</style>
	<script type="text/javascript">
		$(()=>{
			//서머노트 연동
			$("#content").summernote({
				placeholder: '여기에 내용을 입력하세요',
				height: 250
			});
			
			//버튼의 이벤트 연결
			$("input[type='button']").click(()=>{
				$("form").attr({
					action: "/notice/regist",
					//method: "GET",	//머리에 데이터를 실어 나르게 됨, 편지 봉투에 정보를 나르는 격
											//문제1) 노출, 문제2)내용짤림(용량이 크지 않음)
					method: "POST"
				});
				$("form").submit();	//전송
			});
		})
	</script>
</head>
<body>
	<h3>Contact Form</h3>
	
	<div class="container">
	  <form>
	    <label for="fname">Title</label>
	    <input type="text" id="fname" name="title" placeholder="제목을 입력하세요.">
	
	    <label for="lname">Writer</label>
	    <input type="text" id="lname" name="writer" placeholder="작성자를 입력하세요.">
	
	    <label for="subject">Content</label>
	    <textarea id="content" name="content" placeholder="내용을 입력하세요." style="height:200px"></textarea>
	
	    <input type="button" value="Submit">
	  </form>
	</div>

</body>
</html>