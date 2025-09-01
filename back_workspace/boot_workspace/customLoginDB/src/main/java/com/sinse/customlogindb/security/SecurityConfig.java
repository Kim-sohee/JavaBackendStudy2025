package com.sinse.customlogindb.security;


import com.sinse.customlogindb.model.member.MemberDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//스프링이 디폴트로 지원하는 폼로그인을 커스텀 하려면, 설정 클래스가 필요함
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final MemberDetailsService memberDetailsService;

    public SecurityConfig(MemberDetailsService memberDetailsService) {
        this.memberDetailsService = memberDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //AuthenticationProvider 등록
    //개발자 대신 비밀번호를 비교 검증(따라서 개발자는 Repository, DAO 등에서 password를 사용할 필요 없음)
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(memberDetailsService);       //어떤 서비스를 이용할 지
        provider.setPasswordEncoder(passwordEncoder());      //어떤 비밀번호 변환 객체 이용?
        return provider;
    }


    //Authentication Manager 등록
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    //스프링 시큐리티의 처리를 담당하는 객체인 SecurityFilterChain 등록
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(auth->auth.requestMatchers("/main").permitAll().anyRequest().authenticated())
                .formLogin(form->form.loginPage("/loginform")
                        .loginProcessingUrl("/login")
                        .usernameParameter("id")
                        .passwordParameter("pwd")
                        .defaultSuccessUrl("/main", true)
                        .permitAll()
                );

        return http.build();
    }
}
