package com.example.studyspringjwt.util;

import jakarta.servlet.http.Cookie;

public class CookieUtil {

    //쿠키 생성 메소드
    public static Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24*60*60);     //쿠키의 생명주기(Refresh 토큰의 생명과 동일)
        //cookie.setSecure(true);         //Https 통신을 진행할 경우 사용
        //cookie.setPath("/");            //쿠키가 적용될 범위
        cookie.setHttpOnly(true);

        return cookie;
    }
}
