package com.example.chatprectice.dto;

import com.example.chatprectice.domain.Member;
import lombok.Data;

import java.util.Set;

@Data
public class Room {
    private String UUID;
    private String master;
    private String roomName;
    private Set<Member> userList;
}
