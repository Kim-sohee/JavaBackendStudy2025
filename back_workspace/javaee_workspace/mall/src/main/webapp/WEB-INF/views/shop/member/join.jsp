<%@page import="mall.domain.Product"%>
<%@page import="mall.domain.SubCategory"%>
<%@page import="mall.domain.TopCategory"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<% 
	List<TopCategory> topList = (List)request.getAttribute("topList"); 
	List<Product> productList = (List)request.getAttribute("productList"); 
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Ashion Template">
    <meta name="keywords" content="Ashion, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Ashion | Template</title>

    <%@ include file="../inc/head_link.jsp" %>
    
    <style type="text/css">
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
		
		input[type=submit] {
		  background-color: #04AA6D;
		  color: white;
		  padding: 12px 20px;
		  border: none;
		  border-radius: 4px;
		  cursor: pointer;
		}
		
		input[type=submit]:hover {
		  background-color: #45a049;
		}
		
		.container {
		  border-radius: 5px;
		  background-color: #f2f2f2;
		  padding: 20px;
		}
    </style>
</head>

<body>
    <!-- Page Preloder -->
    <%@ include file="../inc/preloader.jsp" %>

    <!-- Offcanvas Menu Begin -->
    <%@ include file="../inc/offcanvas.jsp" %>
    <!-- Offcanvas Menu End -->

    <!-- Header Section Begin -->
    <%@ include file="../inc/header.jsp" %>
    <!-- Header Section End -->

	<!-- 내용 시작 -->
	<div class="container">
		<form id="form1">
		    <input type="text" id="fname" name="id" placeholder="Your ID..">
		    <input type="password" id="lname" name="password" placeholder="Your Password..">
		    <input type="text" id="fname" name="name" placeholder="Your name..">
		    <input type="text" id="lname" name="email" placeholder="Your email..">

		    <input type="button" value="Submit">
	 	 </form>
 	 </div>
	<!-- 내용 끝 -->

	<!-- Instagram Begin -->
	<%@ include file="../inc/instagram.jsp" %>
	<!-- Instagram End -->
	
	<!-- Footer Section Begin -->
	<%@ include file="../inc/footer.jsp" %>
	<!-- Footer Section End -->
	
	<!-- Search Begin -->
	<%@ include file="../inc/search.jsp" %>
	<!-- Search End -->
	
	<!-- Js Plugins -->
	<%@ include file="../inc/footer_link.jsp" %>
	
	<script type="text/javascript">
		function regist(){
			$("#form1").attr({
				action: "/shop/member/regist",
				method: "post"
			});
			$("#form1").submit();
		}
		
		$(()=>{
			
			$("#form1 input[type='button']").click(()=>{
				regist();
			});
		});
	</script>
</body>

</html>