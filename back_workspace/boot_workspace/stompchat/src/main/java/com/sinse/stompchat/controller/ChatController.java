package com.sinse.stompchat.controller;

import com.sinse.stompchat.dto.ChatMessage;
import com.sinse.stompchat.dto.ChatRoom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Controller
public class ChatController {

    /*favicon처리
    * 1) static 디렉토리에 실제로 favicon 이미지를 보유
    * 2) 컨트롤러에 요청을 처리하되, 이미지를 반환하지 않고 void*/
    @GetMapping("favicon.ico")
    @ResponseBody
    public void favicon() {}

    //서버에 접속한 유저 목록
    private Set<String> connectedUsers = new ConcurrentHashMap<>().newKeySet();

    //서버에 생성된 방 목록
    private Map<String, ChatRoom> roomStorage = new ConcurrentHashMap<>();

    /*클라이언트의 접속 요청 처리*/
    //클라이언트가 http://path:port/app/connect로 접속하면 이 메서드 실행

    /*고전적인 방식의 웹 소켓에서는 클라이언트가 전송한 프로토콜에 의해 if문을 사용했지만,
    * STOMP에서는 전통적인 방식이 아닌, 마치 웹요청을 처리하듯 클라이언트의 요청을 구분할 수 있음
    * 따라서 개발자가 별도의 프로토콜을 설계할 필요가 없다.*/
    @MessageMapping("/connect")
    /*이 메서드 실행의 결과는 내부적으로 ObjectMapper에 의한 JSON 문자열이다.
    * 또한 이 메서드 실행 결과를 대상으로 클라이언트를 개발자가 직접 반복문으로 브로드캐스팅을 수행하는 것이 아니라
    * /topic/users 채널을 구독한, 클라이언트 한테 자동으로 전송한다.*/
    @SendTo("/topic/users")
    public Set<String> connect(ChatMessage chatMessage){
        log.debug(chatMessage.getSender()+" 의 접속 요청 받음");
        connectedUsers.add(chatMessage.getSender());
        return connectedUsers;
    }

    //클라이언트의 메시지 전송 처리
    @MessageMapping("/chat.send")
    @SendTo("/topic/messages")      // topic/messages를 구독한 클라이언트들에게 브로드캐스팅
    public ChatMessage send(ChatMessage chatMessage){
        return chatMessage;
    }

    //방 만들기 메시지 전송 처리
    @MessageMapping("/room.create")
    @SendTo("/topic/rooms")     //채널 등록
    public Collection<ChatRoom> createRoom(ChatMessage chatMessage){
        ChatRoom chatRoom = new ChatRoom();
        String roomid = UUID.randomUUID().toString();
        chatRoom.setRoomId(roomid);
        chatRoom.setRoomName(chatMessage.getContent());
        roomStorage.put(roomid, chatRoom);

        return roomStorage.values();
    }

    //밤 목록 요청 처리
    @MessageMapping("/room.list")
    @SendTo("/topic/rooms")
    public Collection<ChatRoom> listRoom(){
        return roomStorage.values();
    }

    //방 참여하기
    @MessageMapping("/room.enter")
    @SendTo("/topic/rooms")
    public Collection<ChatRoom> enterRoom(ChatMessage chatMessage){
        //방을 검색하여, 발견된 방의 Set에 유저명 넣기
        ChatRoom chatRoom = roomStorage.get(chatMessage.getRoomId());
        if(chatRoom != null){
            chatRoom.getUsers().add(chatMessage.getSender());
        }
        return roomStorage.values();
    }

    //방 떠나기
    @MessageMapping("/room.leave")
    @SendTo("/topic/rooms")
    public Collection<ChatRoom> leaveRoom(ChatMessage chatMessage){
        ChatRoom chatRoom = roomStorage.get(chatMessage.getRoomId());
        if(chatRoom != null){
            chatRoom.getUsers().remove(chatMessage.getSender());
        }
        return roomStorage.values();
    }

    //접속 해제 메시지 요청 처리
    @MessageMapping("/disconnect")
    @SendTo("/topic/users")
    public Set<String> disconnect(ChatMessage chatMessage){
        connectedUsers.remove(chatMessage.getSender());
        return connectedUsers;
    }

}