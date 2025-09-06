package com.example.studyspringjwt.jwt;

import com.example.studyspringjwt.dto.CustomUserDetails;
import com.example.studyspringjwt.entity.UserEntity;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@RequiredArgsConstructor
//요청에 대해서 한번만 동작하는 OncePerRequestFilter를 상속받아 구현한다.
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        /* -- 해당 코드는 단일 토큰일 때 사용되는 코드임 ----------------------------------------
        //request에서 Authorization 헤더를 찾음
        String authorization = request.getHeader("Authorization");

        //Authorization 헤더 검증
        if(authorization == null || !authorization.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            //조건이 해당되면 메서드 종료(필수)
            return;
        }

        //Bearer 부분 제거 후 순수 토큰만 획득
        String token = authorization.split(" ")[1];

        //토큰 소멸 시간 검증
        if(jwtUtil.isExpired(token)){
            filterChain.doFilter(request,response);
            return;
        }

        //토큰에서 username과 role 획득
        String username = jwtUtil.getUsername(token);
        String role = jwtUtil.getRole(token);

        //userEntity를 생성하여 값 저장
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        //임시 비밀번호 강제 발급(DB에서 발급 시 요청시마다 DB 접근해야 함->DB부담, ContextHold에는 정확한 비밀번호 넣을 필요X->이미 토큰으로 인증이 끝났음)
        userEntity.setPassword("temppassword");
        userEntity.setRole(role);

        //UserDetails에 회원 정보 객체 담기
        CustomUserDetails customUserDetails = new CustomUserDetails(userEntity);

        //시큐리티 인증 토큰 생성
        Authentication authentication = new UsernamePasswordAuthenticationToken(customUserDetails,null,customUserDetails.getAuthorities());

        //세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request,response);
        --------------------------------------------------------------------------------- */

        //헤더에서 access 키에 담긴 토큰을 꺼냄
        String accessToken = request.getHeader("access");

        //토큰이 없다면 다음 필터로 넘김
        if(accessToken == null){
            filterChain.doFilter(request,response);
            return;
        }

        //토큰 만료 여부 확인, 만료 시 다음 필터로 넘기지 않음
        try {
            jwtUtil.isExpired(accessToken);
        } catch (ExpiredJwtException e) {
            //reponse body
            PrintWriter writer = response.getWriter();
            writer.print("access token expired");
            log.debug("access token expired");

            //response status code
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        //토큰이 access인지 확인(발급 시 페이로드에 명시)
        String category = jwtUtil.getCategory(accessToken);
        if(!category.equals("access")){
            response.getWriter().print("access token invalid");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        //username, role 값을 획득
        String username = jwtUtil.getUsername(accessToken);
        String role = jwtUtil.getRole(accessToken);

        //로그인 시 담긴 정보를 UserDetails 객체에 담기
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setRole(role);
        CustomUserDetails customUserDetails = new CustomUserDetails(userEntity);

        //인증 정보를 SecurityContext에 등록하기
        Authentication authentication = new UsernamePasswordAuthenticationToken(customUserDetails,null, customUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request,response); //다음 필터로 이동
    }
}
