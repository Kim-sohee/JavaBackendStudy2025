package com.sinse.broadcastapp.model.chat;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinse.broadcastapp.dto.ResponseChat;
import com.sinse.broadcastapp.dto.ResponseConnect;
import com.sinse.broadcastapp.dto.User;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/* 웹 소켓을 구현하는 방법
1) 순수 javaEE의 API를 이용하는 방법
2) Spring이 지원하는 API 이용하는 방법

메시지 교환 방법
1) 개발자가 직접 프로토콜을 설계하는 방법
2) STOMP 프로토콜 이용하는 방법
    - WebSocket 위에서 메시지를 주고받기 위한 메시지 프로토콜(개발자가 직접 설계X)

* */
@Slf4j
@ServerEndpoint("/ws/multi")
@Component
public class ChatEndpoint {
    //접속자 명단
   //유저에게 보낼 데이터가 아닌, 서버측에서 사용할 접속자 정보
    private static Set<Session> userList = new HashSet<>();

    //유저에게 접속자 정보를 보내기 위한 Set
    public static Set<String> userIdList = new HashSet<>();
    //java <--> json 과의 변환을 담당해주는 객체
    ObjectMapper objectMapper = new ObjectMapper();

    //연결 감지
    @OnOpen
    public void onOpen(Session session) throws Exception {
        log.debug("onOpen 메서드 호출, 생성된 세션의 id: "+session.getId());
        //서버에서 사용할 Set에 채우기
        userList.add(session);

        //User user = new User();
        //user.setId(session.getId());
        //user.setName("scott");

        //접속과 동시에, 클라이언트에게 서버의 접속자 명단을 구성해서 보내자(프로토콜 형식에 맞게)
        ResponseConnect responseConnect = new ResponseConnect();
        responseConnect.setResponseType("connect");
        userIdList.add(session.getId());
        responseConnect.setData(userIdList);

        String json = objectMapper.writeValueAsString(responseConnect);
        session.getAsyncRemote().sendText(json);
    }

    //메시지 감지
    @OnMessage
    public void onMessage(Session session, String message) throws Exception {
        log.debug("클라이언트의 메시지는 "+message);

        JsonNode jsonNode = objectMapper.readTree(message);
        String requestType = jsonNode.get("requestType").asText();
        if(requestType.equals("chat")){     //대화 요청이라면...
            log.debug("클라이언트가 채팅을 원하는 군요");
            //클라이언트에게 메시지 전송
            //멀티캐스팅이 되려면, 브로드캐스팅 시켜야 한다.
            ResponseChat responseChat = new ResponseChat();
            responseChat.setResponseType("chat");
            responseChat.setSender(session.getId());
            responseChat.setData(jsonNode.get("data").asText());

            String json = objectMapper.writeValueAsString(responseChat);

            for(Session ss: userList){
                if(ss.isOpen()){
                    ss.getAsyncRemote().sendText(json);
                }
            }
        }

//        session.getAsyncRemote().sendText("서버의 메시지는 "+message);
    }

    @OnClose
    public void onClose(Session session) throws Exception {
        //Session이 끊기면 Set에서 제거
        userList.remove(session);
        userIdList.remove(session.getId());     //Session Id 정보도 함께 제거
    }

    @OnError
    public void onError(Session session, Throwable error) throws Exception {
        userList.remove(session);
        userIdList.remove(session.getId());
        error.printStackTrace();
    }
}
