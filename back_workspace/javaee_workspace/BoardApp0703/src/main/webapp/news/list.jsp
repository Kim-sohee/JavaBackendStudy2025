<%@page import="com.sinse.boradapp.util.Paging"%>
<%@page import="com.sinse.boradapp.model.News"%>
<%@page import="java.util.List"%>
<%@page import="com.sinse.boradapp.repository.NewsDAO"%>
<%@page import="org.apache.ibatis.reflection.SystemMetaObject"%>
<%@ page contentType="text/html; charset=UTF-8"%> 
<%! NewsDAO newsDAO = new NewsDAO(); 
		Paging paging = new Paging();
%>
<%
	List<News> list = newsDAO.selectAll();
	paging.init(list, request);

%>
<%="currentPage="+paging.getCurrentPage() %>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta charset="UTF-8">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<style>
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
		.pageNum{
			font-size: 27px;
			font-weight: bold;
			color: red;
		}
		a{text-decoration: none;}
	</style>
	<script>
		$(()=>{
			$("button").click(()=>{
				location.href="/0703/news/write.jsp";
			});
		});
	</script>
</head>
<body>

	<h2>글 목록</h2>
	
	<table>
	  <tr>
	    <th>No</th>
	    <th>글 제목</th>
	    <th>작성자</th>
	    <th>작성일</th>
	    <th>조회수</th>
	  </tr>
	  <% int curPos = paging.getCurPos();
	  		int num = paging.getNum();%>
	  
	  <% for(int i=1; i<=paging.getPageSize(); i++){ %>
	  	<% if(num<1) break; %>
	  	<% News news = list.get(curPos++); %>
		  <tr>
		    <td><%=num-- %></td>
		    <td><a href="/0703/news/content.jsp?news_id=<%=news.getNews_id()%>"><%=news.getTitle() %></a></td>
		    <td><%=news.getWriter() %></td>
		    <td><%=news.getRegdate() %></td>
		    <td><%=news.getHit() %></td>
		  </tr>
	  <%} %>
	  <tr>
	  	<td colspan="5">
	  	<a href="#">◀</a>
	  <%for(int i=paging.getFirstPage(); i<=paging.getLastPage(); i++){ %>
	  	<% if(i>paging.getTotalPage()) break; %>
	  		<a <%if(paging.getCurrentPage()==i){%>class="pageNum"<%} %> href="/0703/news/list.jsp?currentPage=<%=i%>">[<%=i %>]</a>
	  	<%} %>
	  	<a href="#">▶</a>
	  	</td>
	  </tr>
	  <tr>
	  	<td colspan="5"><button>글쓰기</button></td>
	  </tr>
	</table>
</body>
</html>
