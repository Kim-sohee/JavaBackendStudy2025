<%@page import="com.sinse.memberapp.model.Dept"%>
<%@page import="java.util.List"%>
<%@page import="com.sinse.memberapp.repository.DeptDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%! DeptDAO deptDAO = new DeptDAO();
		
	List<Dept> list = deptDAO.selectAll();
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table width="100%">
		<tr>
			<th>부서 번호</th>
			<th>부서명</th>
			<th>근무위치</th>
			<th>근무자 수</th>
		</tr>
		<%for(Dept dept : list){ %>
			<tr>
				<td><%=dept.getDeptno() %></td>
				<td><%=dept.getDname() %></td>
				<td><%=dept.getLoc() %></td>
				<td><%=dept.getEmpList().size() %></td>
			</tr>
		<%} %>
	</table>
</body>
</html>