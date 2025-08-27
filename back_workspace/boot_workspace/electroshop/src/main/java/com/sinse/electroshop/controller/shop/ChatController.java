package com.sinse.electroshop.controller.shop;

import com.sinse.electroshop.websocket.dto.ChatRoom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
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
    public Set<String> connect(){
        roomStorage.get();
        return null;
    }
}
