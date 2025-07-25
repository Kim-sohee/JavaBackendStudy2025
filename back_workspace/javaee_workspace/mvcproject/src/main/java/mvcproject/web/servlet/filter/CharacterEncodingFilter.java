package mvcproject.web.servlet.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter{
	String encoding;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		encoding = filterConfig.getInitParameter("encoding");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//사전 처리
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		
		//다음 필터나 서블리스로 넘김
		chain.doFilter(request, response); 		//이 메서드를 호출해야, 요청의 흐름이 끊기지 않는다.
		
		//doFilter 이후에 사후 처리
	}

	@Override
	public void destroy() {
	}
}
