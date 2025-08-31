package com.example.chatprectice.model.chat;

import com.example.chatprectice.domain.Member;
import com.example.chatprectice.dto.CreateRoomResponse;
import com.example.chatprectice.dto.Room;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.EndpointConfig;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Component
@ServerEndpoint(value="/chat/multi", configurator = HttpSessionConfigurator.class)
public class ChatEndpoint {

    private static Set<Session> userList = new HashSet<>();     //접속자 정보
    private static Set<Member> memberList = new HashSet<>();
    private static Set<Room> roomList = new HashSet<>();

    private static ObjectMapper objectMapper = new ObjectMapper();

    @OnOpen
    public void onOpen(Session session, EndpointConfig endpointConfig) throws Exception {
        HttpSession httpSession=(HttpSession) endpointConfig.getUserProperties().get(HttpSession.class.getName());
        if(httpSession!=null){
            Member member=(Member)httpSession.getAttribute("member");
            session.getUserProperties().put("member",member);
            userList.add(session);

            //응답 정보 생성
            CreateRoomResponse createRoomResponse=new CreateRoomResponse();
            createRoomResponse.setResponseType("createRoom");

            Member user = new Member();
            user.setId(member.getId());
            user.setName(member.getName());
            user.setEmail(member.getEmail());
            memberList.add(user);
            createRoomResponse.setMemberList(memberList);

            String json = objectMapper.writeValueAsString(createRoomResponse);
            session.getAsyncRemote().sendText(json);
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) throws Exception{
        JsonNode jsonNode = objectMapper.readTree(message);
        String requestType = jsonNode.get("requestType").asText();

        if(requestType.equals("createRoom")){
            String userId = jsonNode.get("userId").asText();
            String roomName = jsonNode.get("roomName").asText();

            Member member = (Member)session.getUserProperties().get("member");
            if(!member.getId().equals(userId)){
                //클라이언트에게 에러 전송
            }else{
                UUID uuid = UUID.randomUUID();
                Room room = new Room();
                room.setUUID(uuid.toString());
                room.setMaster(userId);
                room.setRoomName(roomName);

                Set users = new HashSet();

                Member obj = new Member();
                obj.setId(member.getId());
                obj.setName(member.getName());
                obj.setEmail(member.getEmail());
                memberList.add(obj);
                roomList.add(room);

                CreateRoomResponse createRoomResponse=new CreateRoomResponse();
                createRoomResponse.setResponseType("createRoom");
                createRoomResponse.setMemberList(memberList);
                createRoomResponse.setRoomList(roomList);

                for(Session ss : userList){
                    ss.getAsyncRemote().sendText(objectMapper.writeValueAsString(createRoomResponse));
                }
            }
        }
    }
}
