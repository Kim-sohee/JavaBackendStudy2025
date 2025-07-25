<%@page import="com.sinse.boardapp0701.model.Notice"%>
<%@page import="com.sinse.boardapp0701.repository.NoticeDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%! NoticeDAO noticeDAO; %>
<%
	String notice_id = request.getParameter("notice_id");
	noticeDAO = new NoticeDAO();
	
	Notice notice = noticeDAO.select(notice_id);
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
			$('#content').summernote({
				placeholder: '여기에 내용을 입력하세요',
				height: 250
			});
			$("input[type='button']").click(()=>{
				$("form").attr({
					action: "/0701/notice/regist1",
					method: "POST"
				});
				$("form").submit();
			});
		});
	</script>
</head>
<body>
	<h3>Contact Form</h3>
	
	<div class="container">
	  <form>
	    <label for="fname">Title</label>
	    <input type="text" id="fname" name="title" placeholder="제목을 입력하세요." value=<%=notice.getTitle() %>>
	
	    <label for="lname">Writer</label>
	    <input type="text" id="lname" name="writer" placeholder="작성자를 입력하세요." value=<%=notice.getWriter() %>>
	
	    <label for="subject">Content</label>
	    <textarea id="content" name="content" placeholder="내용을 입력하세요." style="height:200px"><%=notice.getContent() %></textarea>
	
	    <input type="button" value="Submit">
	  </form>
	</div>

</body>
</html>