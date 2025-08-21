package com.sinse.bootwebsocket.model.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinse.bootwebsocket.dto.ChatMessage;
import com.sinse.bootwebsocket.dto.ChatRoom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

//javaee 순수 api로 ServerEndpoint를 구현했던 클래스와 같은 역할을 수행하는 클래스
//단, 스프링 기반 api로 구현해본다.
@Slf4j
@Component
public class ChatWebSocketHandler implements WebSocketHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    //총 접속자 목록
    //아래의 객체를 만일 ObjectMapper에게 변환을 맡기면 보안상 중요한 세션 정보가 json으로 전달되어 버린다.
    //따라서 클라이언트는 접속자 아이디만 알면 되므로, connectedUsers라는 Set 별도로 정의하여 관리해야 한다.
    private final Set<WebSocketSession> sessions = new ConcurrentHashMap<>().newKeySet();

    //다중 쓰레드 환경에서 동시성 문제를 이미 해결해놓은 ConcurrentHashMap을 이용하여 Set을 만든다.
    private final Set<String> connectedUsers = new ConcurrentHashMap<>().newKeySet();

    //방 목록 저장소
    private final Map<String, ChatRoom> roomStorage = new ConcurrentHashMap<>();

    //javaee api의 @OnOpen과 동일
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.debug("새 클라이언트가 연결됨: " + session.getId());
        sessions.add(session);
    }

    //거의 모든 클라이언트의 요청마다, 서버는 접속한 클라이언트들을 대상으로 메시지를 전송해야 하므로
    //반복문을 수시로 Session 수만큼 돌려야 한다. 따라서 공통적인 코드이므로, 메서드로 정의한다.
    //또한 이 메서드는 handleMessage() 멤버 메서드에서만 접근할 것이므로, 굳이 public  외부에 공개할 필요 없다.
    private void broadCast(String destination, Object data) throws Exception {
        String json = objectMapper.writeValueAsString(Map.of("destination", destination, "body", data));
        //서버에 현재 접속해있는 모든 클라이언트의 세션만큼 반복
        for(WebSocketSession wss :sessions){
            if(wss.isOpen()) {
                wss.sendMessage(new TextMessage(json));
            }
        }
    }

    //@OnMessage와 동일
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        //클라이언트가 보낸 메시지는 그냥 String일 뿐이므로, 분석을 위해서는 자바의 객체화시켜야 한다.
        //특히 클라이언트가 보낸 String이 json 문자열이므로, 라이브러리 중 json 문자열과 java 객체간 변환을
        //자동으로 처리해주는 jackson 라이브러리를 이용하자.
        ChatMessage chatMessge = objectMapper.readValue(message.getPayload().toString(), ChatMessage.class);
        switch (chatMessge.getType()) {
            //새로운 유저가 접속했으므로, 총 접속자 목록에 추가
            case "CONNECT" ->{
                connectedUsers.add(chatMessge.getSender());
                //모든 접속된 유저들에게 접속자 목록에 대한 브로드 캐스팅
                //브로드캐스팅 시, 클라이언트가 서버가 보낸 메시지를 구분할 수 있는 구분 채널,
                // 또는 구분 값을 포함해서 보내주자.
                broadCast("/users", connectedUsers);
            }
            case "DISCONNECT" ->{
                connectedUsers.remove(chatMessge.getSender());
                broadCast("/users", connectedUsers);
            }
            case "MESSAGE" ->{
                broadCast("/messages", chatMessge);
            }
            case "ROOM_CREATE" ->{
                //방을 생성
                String uuid = UUID.randomUUID().toString();
                ChatRoom chatRoom = new ChatRoom();
                chatRoom.setRoomId(uuid);
                chatRoom.setRoomName(chatMessge.getContent());  //roomName

                roomStorage.put(uuid, chatRoom);
                broadCast("/rooms", roomStorage.values());
            }
            case "ROOM_LIST" ->{

            }
            case "ROOM_ENTER" ->{
                //Map에 모여 있는 룸들 중, 클라이언트가 참여하기를 원하는 룸을 검색하자.
                ChatRoom room = roomStorage.get(chatMessge.getRoomId());
                if(room != null){   //검색된 방이 발견된다면..
                    room.getUsers().add(chatMessge.getSender());    //참여자로 등록
                }
                broadCast("/rooms", roomStorage.values());
            }
            case "ROOM_LEAVE" ->{
                ChatRoom room = roomStorage.get(chatMessge.getRoomId());
                if(room != null){
                    room.getUsers().remove(chatMessge.getSender());     //룸에서 제거
                }
                broadCast("/rooms", roomStorage.values());
            }
        }

        log.debug("클라이언트에서 받은 메시지: " + message.getPayload().toString());


    }

    //@OnError와 동일
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

    //@OnClose와 동일
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
