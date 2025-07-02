<%@ page contentType="text/html; charset=UTF-8"%>
<%
	int totalRecord = 26;	//총 레코드 수
	int pageSize = 10;	//한 페이지당 보여질 레코드 수
	int totalPage = (int)Math.ceil((float)totalRecord/pageSize) ;		//총 페이지 수
	int blockSize = 10; //블럭당 보여질 페이지 수
	
	int currentPage=1;	//현재 유저가 보고 있는 페이지
	if(request.getParameter("currentPage") != null){		//페이지 파라미터가 넘어올때만
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	
	int firstPage = currentPage-(currentPage-1)%blockSize;	//블럭당 시작 페이지
	int lastPage = firstPage + (blockSize-1);	//블럭당 마지막 페이지
	if(lastPage>totalPage){
		lastPage = totalPage;
	}
	
%>

<%="totalRecord="+totalRecord+"<br>" %>
<%="pageSize="+pageSize+"<br>" %>
<%="totalPage="+totalPage+"<br>" %>
<%="blockSize="+blockSize+"<br>" %>
<%="currentPage="+currentPage+"<br>" %>
<%="firstPage="+firstPage+"<br>" %>
<%="lastPage="+lastPage+"<br>" %>
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
			font-weight:bold;
			color: red;
		}
	</style>
</head>
<body>
	<table>
	  <tr>
	    <th>First Name</th>
	    <th>Last Name</th>
	    <th>Points</th>
	  </tr>
	  <% for(int i=0; i<pageSize; i++){ %>
		  <tr>
		    <td><%= i+1 %></td>
		    <td>Smith</td>
		    <td>50</td>
		  </tr>
	  <%} %>
	  <tr>
	  	<td colspan="3" align="center">
	  		<%if(firstPage-1 > 0){ %>
	  			<a href="/board/list.jsp?currentPage=<%=firstPage-1%>">◀</a>
	  		<%} else{ %>
	  			<a href="javascript:alert('이전 페이지가 없습니다.')">◀</a>
	  		<%} %>
	  		<%for(int i=firstPage; i<=lastPage; i++){ %>
	  			<a <%if(currentPage == i){ %> class="pageNum"<%} %> href="/board/list.jsp?currentPage=<%=i%>">[<%= i %>]</a>
	  		<% } %>
	  		<%if(currentPage <= totalPage && totalPage > lastPage){ %>
	  			<a href="/board/list.jsp?currentPage=<%=lastPage+1 %>">▶</a>
	  		<%}else{ %>
	  			<a href="javascript:alert('다음 페이지가 없습니다.')">▶</a>
	  		<% } %>
	  	</td>
	  </tr>
	</table>
</body>
</html>
