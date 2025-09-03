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

//이 서블릿은 로그인에 성공한 클라이언트만이 접근할 수 있다.
//따라서 이 서블릿을 호출한다는 것은 이미 인증을 마친 클라이언트이어야 한다.

@WebServlet("/api/protected")
public class ProtectedApiServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 한 유저의 경우 request 객체에 심어진 데이터를 가져가도록
		String username = (String)request.getAttribute("username");
		
		//응답 정보를 json으로 가져갈 수 있도록 처리
		response.setContentType("application/json; charset=utf-8");
		
		Map<String, String> map = new HashMap<>();
		map.put("message", "보호된 데이터 접근 성공");
		map.put("user", username);
		
		response.getWriter().print(new Gson().toJson(map));
	}
}
