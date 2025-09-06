package com.example.studyspringjwt.service;

import com.example.studyspringjwt.dto.TokenResponseDTO;
import com.example.studyspringjwt.exception.JwtRefreshTokenException;
import com.example.studyspringjwt.jwt.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReissueService {

    private final JwtUtil jwtUtil;

    //클라이언트가 쿠키로 보낸 Refresh Token의 유효성을 확인하여 새로운 Access Token을 발급
    public TokenResponseDTO reissueAccessToken(HttpServletRequest request){
        //refresh 토큰 꺼내기
        String refresh = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("refresh")){
                    refresh = cookie.getValue();
                    break;
                }
            }
        }

        //토큰 존재 확인
        if(refresh == null){
            throw new JwtRefreshTokenException("refresh token is null");
        }

        //만료 여부 체크
        try {
            jwtUtil.isExpired(refresh);
        } catch (ExpiredJwtException e) {
            throw new JwtRefreshTokenException("invalid refresh token");
        }

        //category 확인(발급 시 페이로드에 명시)
        if(!"refresh".equals(jwtUtil.getCategory(refresh))){
            throw new JwtRefreshTokenException("invalid refresh token");
        }

        //정보 꺼내고 새 access 토큰 발급
        String username = jwtUtil.getUsername(refresh);
        String role =  jwtUtil.getRole(refresh);

        String newAccess = jwtUtil.createJwt("access", username, role, 600000L);
        String newRefresh = jwtUtil.createJwt("refresh", username, role, 86400000L);

        return new TokenResponseDTO(newAccess, newRefresh);
    }
}