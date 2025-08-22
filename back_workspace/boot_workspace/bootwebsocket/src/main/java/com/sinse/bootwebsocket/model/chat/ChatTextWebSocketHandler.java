package com.sinse.bootwebsocket.model.chat;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinse.bootwebsocket.dto.ChatMessage;
import com.sinse.bootwebsocket.dto.ChatRoom;
import jakarta.websocket.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/*
* Spring에서 웹 소켓 Server Endpoint를 다루는 객체는 WebSocketHandler만 있는게 아님
* TextWebSocketHandler
* - 다루고자 하는 데이터가 Text일 경우 효율적
* - 인터페이스가 아니므로, 사용되지도 않는 메서드를 재정의할 필요 없다.(즉, 필요한 것만 골라서 재정의)
* */

@Slf4j
@Component
public class ChatTextWebSocketHandler extends TextWebSocketHandler {
    //java - json 문자열과의 변환을 자동으로 처리해주는 객체
    private ObjectMapper objectMapper = new ObjectMapper();

    //현재 서버에 연결되어 있는 모든 클라이언트 세션의 집합
    private Set<WebSocketSession> sessions = new ConcurrentHashMap<>().newKeySet();

    //현재 서버에 접속되어 있는 모든 클라이언트 아이디집합(클라이언트 전송용)
    private Set<String> connectedUsers = new ConcurrentHashMap<>().newKeySet();

    //전체 방 목록 집합
    private Map<String, ChatRoom> roomStorage = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //클라이언트가 접속할 때
        sessions.add(session);
        
    }

    //모든 클라이언트가 동시에 알아야할 정보를 전송할 브로드캐스트 메서드 정의
    //매개변수가 Object인 이유? -> 문자열 변환을 맡길 데이터 형식이 결정되어 있지 않기 때문
    private void broadCast(String destination, Object data) throws Exception {
        String payload = objectMapper.writeValueAsString(Map.of("destination", destination, "body", data));

        //접속한 모든 클라이언트에게 전송
        for(WebSocketSession ss : sessions){
            if(ss.isOpen()){
                ss.sendMessage(new TextMessage(payload));
            }
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        //클라이언트는 java가 이해할 수 없는 json 문자열 형태로 메시지를 전송하므로, 서버 측에서는 해석 필요
        ChatMessage chatMessage = objectMapper.readValue(message.getPayload(), ChatMessage.class);
        
        //클라이언트 요청 분기
        switch (chatMessage.getType()) {
            case "CONNECT" ->{
                //주의: 클라이언트가 접속하자마자 메시지 전송을 보냈을 때, afterConnectionEstablished()메서드에서 처리하는 것이 아니라,
                //메시지를 받은 handleTextMessage가 처리함
                connectedUsers.add(chatMessage.getSender());
                broadCast("/users", connectedUsers);
            }
            case "DISCONNECT" ->{
                connectedUsers.remove(chatMessage.getSender());
                broadCast("/users", connectedUsers);
            }
            case "MESSAGE" ->{
                broadCast("/messages", chatMessage);
            }
            case "ROOM_CREATE" ->{
                ChatRoom room = new ChatRoom();
                String uuid = UUID.randomUUID().toString();
                room.setRoomId(uuid);
                room.setRoomName(chatMessage.getContent());
                roomStorage.put(uuid, room);
                broadCast("/rooms", roomStorage.values());
            }
            case "ROOM_LIST" ->{
                broadCast("/rooms", roomStorage.values());
            }
            case "ROOM_ENTER" ->{
                //방을 검색하여, 검색된 방의 Set에 유저를 추가
                ChatRoom room = roomStorage.get(chatMessage.getRoomId());
                if(room != null){   //검색된 방이 발견된다면..
                    room.getUsers().add(chatMessage.getSender());    //참여자로 등록
                }
                broadCast("/rooms", roomStorage.values());
            }
            case "ROOM_LEAVE" ->{
                ChatRoom room = roomStorage.get(chatMessage.getRoomId());
                if(room != null){
                    room.getUsers().remove(chatMessage.getSender());     //룸에서 제거
                }
                broadCast("/rooms", roomStorage.values());
            }
        }
        
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
    }
}

