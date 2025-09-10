package com.sinse.jwtredis.security;

import com.sinse.jwtredis.filter.JwtAuthFilter;
import com.sinse.jwtredis.model.member.MemberDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.AuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //개발자가 정의한 컨트롤러에서 AuthenticationManager를 사용할 예정이므로, 미리 등록
    //만일 개발자가 필요한 시점에 new를 해버리면, Spring이 관리하는 Bean이 아니게 됨
    @Bean
    public AuthenticationManager authenticationManagerBean(MemberDetailsService mdService, BCryptPasswordEncoder passwordEncoder) throws Exception {
        //AuthenticationManager는 DaoAuthenticationProvider를 통해
        //1) 유저 얻기(by id)
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(mdService);

        //2) 비번 검증(using PasswordEncoder)
        provider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(provider);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf-> csrf.disable())
                //세션 기반 사용X
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //시큐리티에게 인증 받은 회원을 직접 알려주자
                .securityContext(sc-> sc.requireExplicitSave(false))
                //스프링의 디폴트 폼로그인을 비활성화
                .formLogin(form->form.disable())
                //스프링의 기본 로그아웃 비활성화
                .logout(logout -> logout.disable())
                .authorizeHttpRequests(auth->auth
                        //로그인 하지 않아도 접근 가능한 것
                        .requestMatchers("/index.html", "/member/regist.html", "/member/regist", "/member/login.html","/member/login", "/member/refresh", "/member/logout").permitAll()
                        //접근 허용하지 않아야 할 경로(그밖에)
                        .anyRequest().authenticated())
                //JWT 검증 필터를 스프링 시큐리티의 필터 체인 중 어느 부분에 관여해야 할 지 명시
                .addFilterBefore(jwtAuthFilter, AuthenticationFilter.class)
                .build();
    }
}
