package com.sinse.bootwebsocket.model.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

//Spring에서 지원하는 웹 소켓은 여기서 Endpoint를 지정할 수 있다.
//또한, 클라이언트에 요청을 처리하는 객체를 여기서 등록해야 한다.
@RequiredArgsConstructor    //매개변수가 있는 생성자를 자동 생성해주는 롬복의 기능
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    //생성자 자동으로 컴파일 시점에 생성되므로 AutoWired 필요 없음
    private final ChatWebSocketHandler webSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler, "/ws").setAllowedOrigins("*");
    }
}
