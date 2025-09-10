package com.sinse.jwtredis.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

/*JWT 발급, 검증 전용 유틸
* 1) AccessToken : 인증을 받았음을 증명하는 용도의 토큰, 즉 로그인 성공 결과 토큰
                            유효한 AccessToken을 보유한 클라이언트에게는 어떤 자원이든 접근 가능하도록 통과
* 2) RefreshToken : 보안상 만료시간(TTL)이 제한되어 있는 AccessToken을 재발급할 때 검증 용도로 사용할 토큰
*
* 참고 용어) Claim(클레임) : JWT를 구성하는 내용 (sub-userId, JTI-토큰고유값(UUID), ver, deviceId 등)
* */
@Component
public class JwtUtil {
    private final SecretKey key;     //서명에 사용할 비밀키(HMAC-SHA)
    
    //발급자(토큰 생성자-앱 이름)
    private final String issure;

    //AccessToken 만료 기간(분)      - ex) 5m ~ 15m
    private final long accessMinutes;

    //RefreshToken 만료 기간(일)     - ex) 7d ~ 14d
    private final long refreshDays;

    public JwtUtil(@Value("${app.jwt.secret}") String secret,
                   @Value("${app.jwt.issure}") String issure,
                   @Value("${app.jwt.access-minutes}") long accessMinutes,
                   @Value("${app.jwt.refresh-days}") long refreshDays) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.issure = issure;
        this.accessMinutes = accessMinutes;
        this.refreshDays = refreshDays;
    }

    /*-----------------------------------------------------------------
        AccessToken 생성
    * ----------------------------------------------------------------*/
    public String createAccessToken(String userId, int userVersion, String deviceId) {
        Instant now = Instant.now();    //현재 시각
        Instant exp = now.plusSeconds(60*accessMinutes);    //만료 시각

        return Jwts.builder()
                .setIssuer(issure)
                .setSubject(userId)   //로그인 유저 ID
                .setId(UUID.randomUUID().toString())    //토큰의 고유 ID
                .setIssuedAt(Date.from(now))      //생성일
                .setExpiration(Date.from(exp))      //만료일
                
                //개발자가 토큰에 넣고 싶은 내용
                .claim("ver", userVersion)         //버전을 이용한 로그아웃 처리
                .claim("deviceId", deviceId)     //추후 로그아웃 처리할 디바이스

                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /*-----------------------------------------------------------------
        RefreshToken 생성
        - 생명력이 길기 때문에, 보안상 민감하므로 절대 유출되면 안됨
        - 만일 유출되면, 재발급하여 보안상 위험성을 낮추자
    * ----------------------------------------------------------------*/
    public String createRefreshToken(String userId, String deviceId) {
        Instant now = Instant.now();
        Instant exp = now.plusSeconds(24*60*60*refreshDays);

        return Jwts.builder()
                .setIssuer(issure)
                .setSubject(userId)   //로그인 유저 ID
                .setId(UUID.randomUUID().toString())    //토큰의 고유 ID
                .setIssuedAt(Date.from(now))      //생성일
                .setExpiration(Date.from(exp))      //만료일

                .claim("deviceId", deviceId)     //추후 로그아웃 처리할 디바이스
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /*-----------------------------------------------------------------
        토큰 파싱 및 검증(조작되지 않은 내용, 서명, 만료기간)
    * ----------------------------------------------------------------*/
    public Jws<Claims> parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }

    /*-----------------------------------------------------------------
        토큰의 내용 중 필요한 값 추출 도우미
    * ----------------------------------------------------------------*/
    public String getUserId(Claims claims) {
        return claims.getSubject();
    }

    public String getJti(Claims claims) {
        return claims.getId();
    }

    public int getVersion(Claims claims) {
        Object v=claims.get("ver");
        return (v==null)?0:(Integer)v;
    }

    public String getDeviceId(Claims claims) {
        Object d =  claims.get("deviceId");
        return (d==null)?"":(String)d;
    }

    public long getExpireTime(Claims claims) {
        Date d = claims.getExpiration();
        return (d==null)?0L:d.toInstant().getEpochSecond();
    }
}
