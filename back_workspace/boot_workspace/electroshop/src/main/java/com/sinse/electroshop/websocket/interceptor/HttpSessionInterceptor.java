package com.sinse.electroshop.websocket.interceptor;

import com.sinse.electroshop.domain.Member;
import com.sinse.electroshop.domain.Store;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/*아래의 클래스는 WebSocket 연결과정(Handshake)에서 HttpSession 정보를 WebSocket 세션 속성으로
* 옮겨놓기 위한 객체이다.*/
@Slf4j
public class HttpSessionInterceptor implements HandshakeInterceptor {

    //WebSocket 핸드세이크가 시작되기 전에 호출되는 메서드
    //이 타이밍을 놓치지 말고, HttpSession에 들어있는 값을 WebSocket의 Session에 옮겨심기
    //주의) 4번째 매개변수인 attributes가 바로 WebSocket 세션의 attributes이다.
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        //HttpSession에서 Member꺼내기
        ServletServerHttpRequest servletServerHttpRequest = (ServletServerHttpRequest) request;

        //만일 세션이 없을 경우, 새로 생성하지 않기 -> getSession(false);
        HttpSession httpSession = servletServerHttpRequest.getServletRequest().getSession(false);

        if(httpSession != null){
            if(httpSession.getAttribute("member") != null) {
                Member member = (Member) httpSession.getAttribute("member");
                attributes.put("member", member);   //WebSocket의 Session으로 옮겨심기
                log.debug("핸드세이크 시점에 추출한 회원의 이름은 " + member.getId());
            }else if(httpSession.getAttribute("store") != null){
                Store store = (Store) httpSession.getAttribute("store");
                attributes.put("store", store);
            }
        }

        return true;    //true로 반환해야 핸드세이크가 정상적으로 진행됨
    }

    //핸드세이크가 끝난 후 호출되는 메서드
    //보통은 특별히 사용할 일이 없음(로그 기록 정도,,,)
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }
}
