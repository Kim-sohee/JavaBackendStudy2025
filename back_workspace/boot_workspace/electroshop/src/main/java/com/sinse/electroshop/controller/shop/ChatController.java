package com.sinse.electroshop.controller.shop;

import com.sinse.electroshop.domain.Member;
import com.sinse.electroshop.websocket.dto.ChatMessage;
import com.sinse.electroshop.websocket.dto.ChatRoom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.Map;
import java.util.Set;
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
        Member member = (Member)headerAccessor.getSessionAttributes().get("member");
        log.debug("웹 소켓 세션에서 꺼낸 정보는 "+member.getName());
        log.debug("클라이언트 접속과 동시에 보낸 메시지"+chatMessage.getContent());

        //HttpSession에서 사용자 로그인 정보인 Member를 꺼내보자
        //STOMP 기반으로 HttpSession을 꺼내려면 인터셉터 객체를 구현 및 등록해야 한다.

        //1) 내가 참여하지 않았을 경우 이 상품과 관련된 방에 참여하기
        ChatRoom room = roomStorage.get(chatMessage.getRoomId());
        if(room != null){
            room.getCustomers().add(member.getName());
            connectedUsers.add(member.getName());
        }
        
        //2) 내가 참여한 방과 같은 방에 있는 유저들 목록을 얻어와 @SendTo로 보내기
        log.debug("내가 참여한 방과 같은 방에 있는 유저 "+ connectedUsers.toString()+", "+room.getCustomers());
        return connectedUsers;
    }
}
