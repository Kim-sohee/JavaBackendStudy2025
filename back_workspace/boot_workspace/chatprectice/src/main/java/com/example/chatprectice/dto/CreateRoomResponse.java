package com.example.chatprectice.dto;

import com.example.chatprectice.domain.Member;
import lombok.Data;

import java.util.Set;

@Data
public class CreateRoomResponse {
    private String responseType;
    private Set<Member> memberList;
    private Set<Room> roomList;
}
