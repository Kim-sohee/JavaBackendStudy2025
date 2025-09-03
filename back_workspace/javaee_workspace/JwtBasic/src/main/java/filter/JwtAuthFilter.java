package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//회원 로그인이 필요한 모든 서블릿이나, jsp마다 각각 jwt 보유 여부를 검즈아는 코드를 작성하게 되면 코드가 중복되므로,
//필터 수준에서 jwt 보유 여부를 검증하고, 즉 로그인 검증을 처리하고 이 시점에 개발자가 하고 싶은 작업을 진행
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
		
		
	}

	@Override
	public void destroy() {
	}

}
