<%@page import="com.sinse.boradapp.model.News"%>
<%@page import="com.sinse.boradapp.repository.NewsDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%! NewsDAO newsDAO = new NewsDAO(); %>
<% 
	int news_id = Integer.parseInt(request.getParameter("news_id"));
	News news = newsDAO.select(news_id); %>
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
			
			$('#content').summernote('code', "<%=news.getContent()%>");
			
			$("#bt_edit").click(()=>{
				$("#detail_form").attr({
					method: "post",
					action: "/0703/0703/news/update"
				});
				$("#detail_form").submit();
				
			});
			$("#bt_del").click(()=>{
				location.href = "/0703/0703/news/delete?news_id=<%=news_id%>";
			});
			$("#bt_list").click(()=>{
				location.href="/0703/news/list.jsp";
			});
			
			
		})
	</script>
</head>
<body>
	<h3>글 상세 보기</h3>
	
	<div class="container">
	  <form id="detail_form">
	  	<input type="hidden" name="news_id" value="<%=news_id %>" />
	    <label for="fname">Title</label>
	    <input type="text" id="title" name="title" placeholder="제목을 입력하세요." value="<%=news.getTitle()%>">
	
	    <label for="lname">Writer</label>
	    <input type="text" id="writer" name="writer" placeholder="작성자를 입력하세요." value="<%=news.getWriter()%>">
	
	    <label for="subject">Content</label>
	    <textarea id="content" name="content" placeholder="내용을 입력하세요." style="height:200px"></textarea>
	
	    <input type="button" value="수정하기" id="bt_edit">
	    <input type="button" value="삭제하기" id="bt_del">
	    <input type="button" value="목록보기" id="bt_list">
	  </form>
	</div>

</body>
</html>