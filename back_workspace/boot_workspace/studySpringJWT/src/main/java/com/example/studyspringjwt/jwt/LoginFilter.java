package com.example.studyspringjwt.jwt;

import com.example.studyspringjwt.entity.RefreshEntity;
import com.example.studyspringjwt.model.user.RefreshRepository;
import com.example.studyspringjwt.util.CookieUtil;
import com.example.studyspringjwt.util.RefreshUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collection;
import java.util.Iterator;

@Slf4j
@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    private final RefreshRepository refreshRepository;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //클라이언트 요청에서 username, password 추출
        String username = obtainUsername(request);
        String password = obtainPassword(request);

        log.debug("username: "+username+" password: "+password);

        //스프링 시큐리티에서 username과 password를 검증하기 위해서는 token에 담아야 한다.(username, password, role)
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password, null);
        //token에 담은 검증을 위한 AuthenticationManager로 전달
        return authenticationManager.authenticate(authToken);
    }

    //로그인 성공시 실행하는 메소드 (여기서 JWT를 발급하면 됨)
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {
        log.debug("successful Authentication");

//        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
//        String username = customUserDetails.getUsername();      //이름 얻어오기

        String  username = authentication.getName();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();  //role 얻어오기

//        String token = jwtUtil.createJwt(username, role, 60*60*10L);    //토큰 생성
//        response.addHeader("Authorization", "Bearer " + token);     //response에 담아서 응답하기

        //2개의 토큰을 발급하기(Refresh/Access)
        String access = jwtUtil.createJwt("access", username, role, 600000L);
        String refresh = jwtUtil.createJwt("refresh", username, role, 86400000L);

        //Refresh 토큰 저장
        RefreshEntity entity = RefreshUtil.addRefreshEntity(username, refresh, 86400000L);
        refreshRepository.save(entity);

        //응답 설정
        response.setHeader("access", access);       //access: localStorage
        response.addCookie(CookieUtil.createCookie("refresh", refresh));   //refresh: http only cookie
        response.setStatus(HttpServletResponse.SC_OK);

    }
    //로그인 실패시 실행하는 메소드
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        log.debug("Fail Authentication");
        response.setStatus(401);
    }
}
