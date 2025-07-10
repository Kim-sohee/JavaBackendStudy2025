<%@page import="mvcproject.blood.model.BloodManager"%>
<%@page import="java.io.PrintWriter"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	/*모델 1방식: JSP 또는 서블릿이 MVC 중 VC를 담당하는 개발방식
	   모델 2방식: MVC 패턴을 JavaEE 기술로 구현해놓은 모델
	   				Model: .java(Plain Old Java Object) 
	   		   참고)POJO유래: 초창기 Java가 세상에 이름을 알리기 시작하자, 엔터프라이즈 시장을 노림..
								  컴포넌트 기반의 JAVA기술 -> 기업용 자바(JavaEE)
								  javaEE(EJB-많이는 쓰였으나, 자바 기술을 너무 벗어남..순수한 자바 기술로 보기 힘들정도..트랜잭션 자동처리, 예외, 이메일..)
								  로드 존슨이 책을 씀(Expert one-on-one) - EJB는 자바가 아니다..
								  시범) 순수자바(POJO)+xml 만으로도 자동 트랜잭션이 가능하다고 증명
								  로드 존슨은 자신이 만든 프로그램을 가리켜 스프링이라고 함.
					View: 보여지는 부분은 jsp로 구현
					Controller: Servlet 으로 구현
*/

	String msg = null;
	out.print(msg);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/blood.do" method="post">
		<select name="blood">
			<option value="">혈액형 선택</option>
			<option value="A">A형</option>
			<option value="B">B형</option>
			<option value="O">O형</option>
			<option value="AB">AB형</option>
		</select>
		<button>전송</button>
	</form>
</body>
</html>