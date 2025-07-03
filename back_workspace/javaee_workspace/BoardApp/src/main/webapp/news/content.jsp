<%@page import="com.sinse.boardapp.model.Comment"%>
<%@page import="java.util.List"%>
<%@page import="com.sinse.boardapp.repository.CommentDAO"%>
<%@page import="com.sinse.boardapp.model.News"%>
<%@page import="com.sinse.boardapp.repository.NewsDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%! NewsDAO newsDAO = new NewsDAO(); 
	CommentDAO commentDAO = new CommentDAO();
%>
<%
	//내장객체
	String news_id = request.getParameter("news_id");
	out.print("news_id="+news_id);
	News news = newsDAO.select(Integer.parseInt(news_id));
	
	List<Comment> commentList = commentDAO.selectByNewsId(Integer.parseInt(news_id));
%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta charset="UTF-8">
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
		table {
		  border-collapse: collapse;
		  border-spacing: 0;
		  width: 100%;
		  border: 1px solid #ddd;
		}
		
		th, td {
		  text-align: left;
		  padding: 16px;
		}
		
		tr:nth-child(even) {
		  background-color: #f2f2f2;
		}
	</style>
	<%@ include file="/inc/head_link.jsp" %>
	<script type="text/javascript">
		$(()=>{
			//서머노트 연동
			$("#content").summernote({
				placeholder: '여기에 내용을 입력하세요',
				height: 250
			});
			$("#content").summernote("code", "<%=news.getContent()%>");
			
			//버튼의 이벤트 연결
			$("#bt_news_edit").click(()=>{
				$("#news_form").attr({
					method: "post",
					action: "/news/update"
				});
				$("#news_form").submit();	//전송
			});
			$("#bt_news_del").click(()=>{
				location.href = "/news/del?news_id=<%=news_id%>";
			});
			$("#bt_news_list").click(()=>{
				location.href = "/news/list.jsp";
			});
			
			//댓글에 이벤트 연결
			$("#bt_comment_regist").click(()=>{
				$("#comment_form").attr({
					method: "post",		//HTTP 프로토콜 통신에 사용되는 데이터 구성(Payload) body에 탑재됨.
					action: "/comment/regist"
				});
				$("#comment_form").submit();	//전송
			});
		});
	</script>
</head>
<body>
	<h3>Contact Form</h3>
	
	<div class="container">
	  <form id="news_form">
	  	<input type="hidden" name="news_id" value="<%=news_id%>"/>
	    <label for="fname">Title</label>
	    <input type="text" id="fname" name="title" placeholder="제목을 입력하세요." value="<%=news.getTitle()%>">
	
	    <label for="lname">Writer</label>
	    <input type="text" id="lname" name="writer" placeholder="작성자를 입력하세요." value="<%=news.getWriter()%>">
	
	    <label for="subject">Content</label>
	    <textarea id="content" name="content" placeholder="내용을 입력하세요." style="height:200px"></textarea>
	
	    <input type="button" value="수정" id="bt_news_edit">
	    <input type="button" value="삭제" id="bt_news_del">
	    <input type="button" value="목록" id="bt_news_list">
	  </form>
	  <div id="comment_header">
	  	<form id="comment_form">
		  	<input type="text" style="width:70%" name="msg">
		  	<input type="text" style="width:20%" name="user">
		  	<input type="hidden" name="news_id" value="<%=news.getNews_id()%>">
		  	<input type="button" value="등록" id="bt_comment_regist">
	  	</form>
	  </div>
	  	  
	  <div id="comment_content">
	  	<table>
	  		<tr>
	  			<th>댓글 제목</th>
	  			<th>작성자</th>
	  			<th>등록일</th>
	  		</tr>
	  		<%for(Comment comment : commentList){ %>
		  		<tr>
		  			<td><%=comment.getMsg() %></td>
		  			<td><%=comment.getUser() %></td>
		  			<td><%=comment.getMsgdate() %></td>
		  		</tr>
	  		<%} %>
	  	</table>
	  </div>
	</div>
</body>
</html>