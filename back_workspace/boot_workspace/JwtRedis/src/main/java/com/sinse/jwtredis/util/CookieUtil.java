package com.sinse.jwtredis.util;

public class CookieUtil {
    //쿠키가 클라이언트 측에 저장되는 기술은 맞지만,  해당 쿠키를 생성하는 방법은 서버측에서 응답  정보로 포함시킬 수도 있다.
    //jsp/servlet에서도 쿠키를 클라이언트 측에 생성하는 것이 가능하다.
    //예) 브라우저에게 어떤 쿠키를 만들지 서버가 결정할 수 있다.

    //쿠키 생성
    //리프레시 토큰은 보안상 중요하므로, 클라이언트 측의 js가 절대로 코드로 접근할 수 없도록
    //Http only 속성을 ture로 세팅해서 응답을 보내자
    public static void setRefreshCookie(){
        
    }

    //쿠키 삭제
}
