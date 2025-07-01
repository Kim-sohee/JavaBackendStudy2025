<%@page import="java.util.List"%>
<%@page import="com.sinse.boardapp0701.model.Notice"%>
<%@page import="com.sinse.boardapp0701.repository.NoticeDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%> 
<%! NoticeDAO noticeDAO; %>
<% 
	noticeDAO = new NoticeDAO();
	List<Notice> list = noticeDAO.selectAll(); %>
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
	</style>
	<script type="text/javascript">
		$(()=>{
			$("button").click(()=>{
				location.href = "/0701/notice/write.jsp";
			});
		});
	</script>
</head>
<body>

	<h2>Zebra Striped Table</h2>
	<p>For zebra-striped tables, use the nth-child() selector and add a background-color to all even (or odd) table rows:</p>
	
	<table>
	  <tr>
	    <th>No</th>
	    <th>제목</th>
	    <th>작성자</th>
	    <th>작성일</th>
	    <th>조회수</th>
	  </tr>
	  <%for(int i=0; i<list.size(); i++){ %>
	  	<% Notice notice = list.get(i); %>
		  <tr>
		    <td>0</td>
		    <td><a href="/0701/notice/content.jsp?notice_id=<%=notice.getNotice_id()%>"><%=notice.getTitle() %></td>
		    <td><%=notice.getWriter() %></td>
		    <td><%=notice.getRegdate() %>></td>
		    <td><%=notice.getHit() %></td>
		  </tr>
	  <%} %>
	  <tr>
	  	<td colspan="5"><button>글 등록</button></td>
	  </tr>
	</table>
</body>
</html>
