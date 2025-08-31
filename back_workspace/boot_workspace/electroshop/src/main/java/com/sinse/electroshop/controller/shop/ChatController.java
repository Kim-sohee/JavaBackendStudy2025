package com.sinse.electroshop.controller.shop;

import com.sinse.electroshop.domain.Member;
import com.sinse.electroshop.domain.Store;
import com.sinse.electroshop.websocket.dto.ChatMessage;
import com.sinse.electroshop.websocket.dto.ChatRoom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

/*Spring의 STOMP를 이용하면 웹소켓을 일반 컨트롤러로 제어할 수 있다.
* 마치 웹 요청을 처리하듯...*/
@Slf4j
@Controller
public class ChatController {
    //서버에 접속한 모든 유저 목록
    private Set<String> connectedUsers = new ConcurrentHashMap<>().newKeySet();

    //서버에 존재하는 모든 방목록 (상품의 수와 일치)
    private Map<String, ChatRoom> roomStorage = new ConcurrentHashMap<>();

    //해당 방에 참여한 사용자 목록

    /*-----------------------------------------------------------
    *  접속 요청 처리
    ----------------------------------------------------------- */
    @MessageMapping("/connect")
    @SendTo("/topic/connected")
    public Set<String> connect(ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        //SimpMessageHeaderAccessor 객체를 이용하면 WebSocket의 Session에 들어있는 정보를 추출

        ChatRoom chatRoom = null;
        int product_id = Integer.parseInt(chatMessage.getContent());

        if(headerAccessor.getSessionAttributes().get("member") != null) {
            Member member = (Member) headerAccessor.getSessionAttributes().get("member");
            log.debug("웹 소켓 세션에서 꺼낸 정보는 " + member.getName());
            log.debug("클라이언트 접속과 동시에 보낸 메시지" + chatMessage.getContent());

            //2) 일반회원이 접속하면, 해당 채팅방에 추가하기
            //일반 회원은 개설된 방에 참여하면 됨..
            for(ChatRoom room : roomStorage.values()) {
                if(room.getProduct_id() == product_id) {
                   chatRoom = room;     //고객이 참여중인 방 발견
                   break;
                }
            }
            chatRoom.getCustomers().add(member.getId());

        }else if(headerAccessor.getSessionAttributes().get("store") != null) {
            Store store = (Store) headerAccessor.getSessionAttributes().get("store");
            log.debug("웹 소켓 세션에서 꺼낸 정보는 " + store.getStoreName());
            log.debug("클라이언트 접속과 동시에 보낸 메시지" + chatMessage.getContent());
            log.debug("전달받은 store 값: "+store);

            //룸을 추가하기 전에 중복 여부 판단하기
            boolean exists = false;     //중복 여부를 판단할 수 있는 기준 변수

            for(ChatRoom room : roomStorage.values()) {
                if(room.getProduct_id() == product_id) {
                    exists = true;
                    chatRoom = room;
                    break;
                }
            }

            //1) 상점이 접속하면, 채팅방을 개설하자(단, 중복 개설하지 않기)
            if(!exists) {
                chatRoom = new ChatRoom();
                UUID uuid = UUID.randomUUID();
                chatRoom.setRoomId(uuid.toString());
                chatRoom.setProduct_id(product_id);

                if(chatRoom.getCustomers() == null) chatRoom.setCustomers(new HashSet<>());
                chatRoom.getCustomers().add(store.getBusinessId());

                //생성된 방을 전체 룸 리스트에 추가하기
                roomStorage.put(chatRoom.getRoomId(), chatRoom);
            }

            //방 참여하기
            chatRoom.getCustomers().add(store.getBusinessId());
        }

        //HttpSession에서 사용자 로그인 정보인 Member를 꺼내보자
        //STOMP 기반으로 HttpSession을 꺼내려면 인터셉터 객체를 구현 및 등록해야 한다.

        return chatRoom.getCustomers();
    }

    //메시지 요청 처리
    @MessageMapping("/chat.send")
    @SendTo("/topic/messages")
    public ChatMessage send(ChatMessage chatMessage) {
        log.debug(chatMessage.getSender()+"가 전송한 메시지 "+chatMessage.getContent());
        return chatMessage;
    }
}
