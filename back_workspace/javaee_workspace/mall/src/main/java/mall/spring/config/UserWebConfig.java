package mall.spring.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth20Service;


/*스프링의 고전적 설정을 담당하는 xml을 대신하는 자바클래스*/
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"mall.shop.controller"})
public class UserWebConfig {
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	//구글 로그인 관련 서비스 객체 등록
	@Bean
	public OAuth20Service googleAuthService() {
		//클라이언트 ID, Secret, 리소스 owner 접근 범위, 콜백 주소
		ServiceBuilder builder = new ServiceBuilder("클라이언트ID");
		builder.apiSecret("비밀번호키");
		builder.defaultScope("email profile openid");
		builder.callback("http://localhost:8888/shop/callback/sns/google");
		
		return builder.build(GoogleApi20.instance());
	}
}