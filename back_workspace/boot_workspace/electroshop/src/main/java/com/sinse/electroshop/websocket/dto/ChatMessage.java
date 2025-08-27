package com.sinse.electroshop.websocket.dto;

import lombok.Data;

@Data
public class ChatMessage {
    private String type;    //클라이언트의 요청을 구분하기 위한 구분값
    private String sender;
    private String content;
    private String roomId;
}
