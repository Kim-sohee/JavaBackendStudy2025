<%@ page contentType="text/html; charset=UTF-8"%>
<%
	int totalRecord = 26;
	int pageSize = 10;
	int totalPage = (int)Math.ceil((float)totalRecord/pageSize);
	int blockSize = 10;
	
	int currentPage=1;
	if(request.getParameter("currentPage") != null){
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	
	int firstPage = currentPage - (currentPage-1)%blockSize;
	int lastPage = firstPage + (blockSize-1);
	if(lastPage>totalPage) lastPage = totalPage;
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Insert title here</title>
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
			font-size: 25px;
			font-weight: bold;
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
	  
	  <%for(int i=0; i<pageSize; i++){ %>
		  <tr>
		    <td><%=(i+1) %></td>
		    <td>Smith</td>
		    <td>50</td>
		  </tr>
		<%} %>
		<tr>
			<td colspan="3">
				<%if(firstPage-1 > 0) { %>
					<a href="/0701/board/list.jsp?currentPage=<%=firstPage-1%>">◀</a>
				<%}else{ %>
					<a href="javascript:alert('이전 페이지가 없습니다.')">◀</a>
				<%} %>
				<%for(int i=firstPage; i<=lastPage; i++){ %>
					<a <%if(currentPage==i){ %> class="pageNum" <%} %> href="/0701/board/list.jsp?currentPage=<%=i%>">[<%=i %>]</a>
				<%} %>
				<%if(currentPage <= totalPage && totalPage > lastPage){ %>
					<a href="/board/list.jsp?currentPage=<%=lastPage+1 %>">▶</a>
				<%}else{ %>
					<a href="javascript:alert('다음 페이지가 없습니다.')">▶</a>
				<%} %>
			</td>
		</tr>
	</table>
	
</body>
</html>