package com.sinse.bootwebsocket.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ChatRoom {
    private String roomId;
    private String roomName;
    private Set<String> users = new HashSet<>();

}
