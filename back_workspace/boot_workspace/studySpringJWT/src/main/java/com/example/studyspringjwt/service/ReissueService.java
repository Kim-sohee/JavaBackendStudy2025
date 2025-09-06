package com.example.studyspringjwt.service;

import com.example.studyspringjwt.dto.TokenResponseDTO;
import com.example.studyspringjwt.entity.RefreshEntity;
import com.example.studyspringjwt.exception.JwtRefreshTokenException;
import com.example.studyspringjwt.jwt.JwtUtil;
import com.example.studyspringjwt.model.user.RefreshRepository;
import com.example.studyspringjwt.util.RefreshUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReissueService {

    private final JwtUtil jwtUtil;
    private final RefreshRepository refreshRepository;

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
            throw new JwtRefreshTokenException("refresh token is null: 토큰이 존재하지 않습니다.");
        }

        //만료 여부 체크
        try {
            jwtUtil.isExpired(refresh);
        } catch (ExpiredJwtException e) {
            throw new JwtRefreshTokenException("invalid refresh token: 토큰이 만료되었습니다.");
        }

        //category 확인(발급 시 페이로드에 명시)
        if(!"refresh".equals(jwtUtil.getCategory(refresh))){
            throw new JwtRefreshTokenException("invalid refresh token: 리프레시 토큰 형식이 아닙니다.");
        }

        //DB에 저장되어 있는지 확인
        Boolean isExist = refreshRepository.existsByRefresh(refresh);
        if(!isExist){
            throw new JwtRefreshTokenException("invalid refresh token: 저장된 토큰이 없습니다.");
        }

        //정보 꺼내고 새 access 토큰 발급
        String username = jwtUtil.getUsername(refresh);
        String role =  jwtUtil.getRole(refresh);

        //새로운 JWT 토큰 생성
        String newAccess = jwtUtil.createJwt("access", username, role, 600000L);
        String newRefresh = jwtUtil.createJwt("refresh", username, role, 86400000L);

        //Refresh Token 저장 DB에 기존의 Refresh 토큰 삭제 후 새 토큰 저장
        refreshRepository.deleteByRefresh(refresh);
        RefreshEntity entity = RefreshUtil.addRefreshEntity(username, newRefresh, 86400000L);
        refreshRepository.save(entity);

        return new TokenResponseDTO(newAccess, newRefresh);
    }
}