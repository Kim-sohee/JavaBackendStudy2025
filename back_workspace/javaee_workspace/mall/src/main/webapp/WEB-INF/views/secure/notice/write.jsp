<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta charset="UTF-8">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
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
			//버튼의 이벤트 연결
			$("input[type='button']").click(()=>{
				$("form").attr({
					action: "/admin/notice/regist",
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