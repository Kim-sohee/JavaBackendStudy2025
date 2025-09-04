package filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import util.MyJwtUtil;

//회원 로그인이 필요한 모든 서블릿이나, jsp마다 각각 jwt 보유 여부를 검즈아는 코드를 작성하게 되면 코드가 중복되므로,
//필터 수준에서 jwt 보유 여부를 검증하고, 즉 로그인 검증을 처리하고 이 시점에 개발자가 하고 싶은 작업을 진행
@WebFilter("/api/*")
public class JwtAuthFilter implements Filter{

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//필터 수준에서 들어오는 요청에 Jwt에 대한 검증을 진행
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		//Jwt가 헤더를 통해 전송되므로, 헤더를 접근해보자.
		String authHeader = req.getHeader("Authorization"); 	//여러 헤더 값 중 jwt 토큰이 전달되는 헤더는 Authorization 헤더임
		
		//Bearer 토큰 - bearer 소지자,  소유자
		//클라이언트가 요청 시 헤더에 지참했던 토큰을 꺼내보자!!
		//주의! 꺼내기 전에 토큰이 존재할 경우만 꺼내야 한다.
		if(authHeader==null || !authHeader.startsWith("Bearer ")) {
			//클라이언트에게 jwt가 없다는 에러 메시지를 전송해야 함
			res.setContentType("application/json; charset=utf-8");
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			
			Map<String, String> map = new HashMap<>();
			map.put("error", "로그인 정보가 올바르지 않습니다.");
			res.getWriter().print(new Gson().toJson(map));
			
			return;		//현재 이 필터 실행을 빠져나가게 처리
		}
		
		//토큰을 가져온 경우, 순수 토큰 값을 꺼내서 검증..
		String token = authHeader.substring(7);
		
		if(MyJwtUtil.validateToken(token) == false) {
			//클라이언트에게 jwt가 없다는 에러 메시지를 전송해야 함
			res.setContentType("application/json; charset=utf-8");
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			Map<String, String> map = new HashMap<>();
			map.put("error", "로그인이 필요한 서비스입니다.");
			res.getWriter().print(new Gson().toJson(map));
			
			return;
		}
		
		//위의 if문을 만나지 않고 무사히 통과했다면, 유효한 토큰을 가진 것이므로, 개발자가 원하는 처리
		String username = MyJwtUtil.getUsername(token);
		//인증에 성공한 회원에게는 username를 사용할 수 있는 기회를 제공
		req.setAttribute("username", username);
		
		//필터가 요청을 가로막았으므로 업무처리가 끝난 후에는 요청을 다시 흐름대로 흐르게
		chain.doFilter(request, response);		//다음 서블릿 또는 다른 필터로 요청 전달
	}

	@Override
	public void destroy() {
	}

}
