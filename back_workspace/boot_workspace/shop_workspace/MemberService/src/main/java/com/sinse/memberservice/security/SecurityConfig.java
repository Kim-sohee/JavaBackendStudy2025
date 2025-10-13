package com.sinse.memberservice.security;

import com.sinse.memberservice.handler.OAuth2SuccessHandler;
import com.sinse.memberservice.model.member.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .formLogin(formLogin -> formLogin.disable())
                .httpBasic(httpBasic -> httpBasic.disable())
                .authorizeHttpRequests(auth-> auth
                        .requestMatchers("/memberapp/oauth2/**").permitAll()
                        .anyRequest().authenticated())
                /*아래의 oauth2Login 등록되는 시점부터 Security6의 OAuth2 기반 인증이 시작!
                * OAuth2AuthorizationRequestRedirectFilter가 이 시점부터 작동!
                * 주의할 점) 이 필터가 리다이렉트할 Provider의 요청 주소가 이미 정해진 형식
                *               /oauth2/authorization/{provider_id}로 정해져 있기 때문에, 
                *               요청시 스프링 시큐리티가 이해할 수 없는 접두어가 있다면 반드시 제거해줘야 이 필터가 동작함...
                * */
                .oauth2Login(oauth2->oauth2
                        .userInfoEndpoint(ui->ui.userService(customOAuth2UserService))
                        .defaultSuccessUrl("/memberapp/login/ok", true)
                        .successHandler(oAuth2SuccessHandler));

        return http.build();
    }
}
