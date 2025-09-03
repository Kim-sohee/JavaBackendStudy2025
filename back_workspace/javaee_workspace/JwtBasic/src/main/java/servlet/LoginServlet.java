package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import util.MyJwtUtil;

@WebServlet("/member/login")		//web.xml을 사용하지 않고, 어노테이션 기반으로 서블릿을 매핑하는 방법
public class LoginServlet extends HttpServlet{
	String id = "batman";
	String pwd = "1234";
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 받기
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println("username is "+username+", password is "+password);
		
		//DB 연동하는 대신, 조건문으로 인증을 처리하자
		if(id.equals(username) && pwd.equals(password)) {
			//인증 증표를 생성하여 클라이언트를 응답 정보로 보내주자
			String accessToken = MyJwtUtil.generateAccessToken(username, 1000*60*15);		//15분 동안 유효
			
			//클라이언트에게 발급(응답 정보로 전송)
			//응답 본문의 데이터 형식을 지정하여 브라우저가 본문을 json으로 해석하도록 지시
			response.setContentType("application/json; charset=utf-8");
			
			//Gson으로 더 편하게 처리해보자
			//java 클래스를 정의해도 되지만, 귀찮을 경우 Map을 json 문자열로 변환시키자
			Map<String, String> tokens = new HashMap<>();
			tokens.put("accessToken", accessToken);
			
			Gson gson = new Gson();
			String json = gson.toJson(tokens);
			response.getWriter().print(json);
		}
	}
}
