package com.example.studyspringjwt.jwt;

import io.jsonwebtoken.Jwts;
import lombok.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {
    //JWT 서명에 사용될 비밀키를 설정하는 생성자, properties의 암호키 값을 읽어와 HS256 알고리즘으로 서명할 수 있도록 SecretKey에 초기화
    private SecretKey secretKey;
    private final JwtProperties jwtProperties;

//    public JwtUtil(@Value("${spring.jwt.secret}")String secret) {
//        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
//    }
    public JwtUtil(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        this.secretKey = new SecretKeySpec(jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    //검증 진행
    public String getUsername(String token) {   //JWT에서 username 값을 추출하는 메서드
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("username", String.class);
    }
    public String getRole(String token) {   //JWT에서 role 값을 추출하는 메서드
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
    }
    public Boolean isExpired(String token) {    //JWT가 만료되었는지 확인하는 메서드
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    //JWT 토큰 생성
    //username + role 정보를 담아서 JWT를 생성한다.
    public String createJwt(String username, String role, Long expiredMs) {
        return Jwts.builder()
                .claim("username", username)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))       //만료시간을 설정
                .signWith(secretKey)
                .compact();
    }
}
