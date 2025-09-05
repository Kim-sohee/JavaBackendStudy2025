package com.sinse.jwtlogin.util;


import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")     //application.properties 파일에 등록된 키 가져오기
    private String secretKey;

    @Value("${jwt.expiration}")
    private long accessTokenExpireTime;     //유효기간

    @Value("${jwt.refresh.expiration}")
    private long refreshTokenExpireTime;     //유효기간

    //AccessToken 발급
    public String generateAccessToken(String username) {
        return generateToken(username, accessTokenExpireTime);
    }

    //RefreshToken발급
    public String generateRefreshToken(String username){
        return generateToken(username, refreshTokenExpireTime);
    }


    //토큰 생성
    private String generateToken(String username, long exp){
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + exp))
                .setIssuedAt(new Date())  //이 토큰의 발급 시점
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes( )), SignatureAlgorithm.HS256)
                .compact();
    }

    //토큰 검증
    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                    .build()
                    .parseClaimsJws(token);
            return true;
        }catch (JwtException e){
            e.printStackTrace();
            return false;
        }
    }

    //회원 id 꺼내기
    public String getUsername(String token){
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
