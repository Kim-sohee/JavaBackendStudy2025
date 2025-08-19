package com.sinse.chatroomapp.model.chat;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinse.chatroomapp.domain.Member;
import com.sinse.chatroomapp.dto.Room;
import com.sinse.chatroomapp.dto.RoomResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.EndpointConfig;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Component
@ServerEndpoint(value="/chat/multi", configurator = HttpSessionConfigurator.class)
public class ChatEndpoint {
    //접속자 명단
    private static Set<Session> userList = new HashSet<>();     //서버 측에서 필요한 접속자 정보
    private static Set<Member> memberList = new HashSet<>();    //클라이언트에게 전달할 접속자 정보
    private static Set<Room> roomList = new HashSet<>();        //클라이언트에게 전달할 룸 정보

    private static ObjectMapper objectMapper = new ObjectMapper();

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) throws IOException {
        HttpSession httpSession = (HttpSession)config.getUserProperties().get(HttpSession.class.getName());

        if(httpSession != null){
            Member member = (Member)httpSession.getAttribute("member");

            //클라이언트 브라우저에서는 접속자 명단만 필요하므로, 서버측에서 너무나 예민한 정보는 클라이언트에게 보내줄 필요X
            //따라서 member 모델에서 id만 추출해보자
            session.getUserProperties().put("member", member);
            userList.add(session);      //접속자 명단에 현재 웹소켓 세션 추가
            
            //접속한 클라이언트가 알아야할 정보 전송
            //단, 클라이언트와의 통신은 정해진 프로토콜을 지켜 보내자
            /* 프로토콜 요청 예시
            * {
            *       responseType:"createRoom",
            *       memberList: [
            *           {
            *                   id: "mario",
            *                   name: "마리오",
            *                   email: "zino1234@nave.com"
            *           }
            *       ],
            *       roomList: [
            *       ]
            * }
            * */
            //응답정보 만들기
            RoomResponse roomResponse = new RoomResponse();
            roomResponse.setResponseType("createRoom");

            //회원 정보 채우기
            Member obj = new Member();
            obj.setId(member.getId());
            obj.setName(member.getName());
            obj.setEmail(member.getEmail());
            memberList.add(obj);

            roomResponse.setMemberList(memberList);

            String json = objectMapper.writeValueAsString(roomResponse);
            session.getAsyncRemote().sendText(json);
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        log.debug(message);

        //파싱
        JsonNode jsonNode = objectMapper.readTree(message);
        String requestType = jsonNode.get("requestType").asText();

        if(requestType.equals("createRoom")) {
            //방 생성하기 요청이라면
            log.debug("방 만들어 줄게");
            String userId = jsonNode.get("userId").asText();
            String roomName = jsonNode.get("roomName").asText();

            //로그인 시 사용된 HttpSession에 들어있는 회원정보와, 웹소켓을 통해 전달된 회원정보를 비교하여 일치하는지 검증
            Member member = (Member)session.getUserProperties().get("member");
            if(!member.getId().equals(userId)) {
                //클라이언트에게 에러를 전송!

            }else{
                //방 고유 식별자
                UUID uuid = UUID.randomUUID();
                Room room = new Room();
                room.setUUID(uuid.toString());
                room.setMaster(userId);
                room.setRoomName(roomName);

                //참여자 목록
                Set users = new HashSet();

                Member obj = new Member();
                obj.setId(member.getId());
                obj.setName(member.getName());
                obj.setEmail(member.getEmail());

                users.add(obj);
                room.setUsers(users);

                roomList.add(room);

                /*클라이언트에게 전송할 응답 프로토콜
                *{
                *   responseType: "createRoom",
                *   roomList : [{
                *       UUID: "dafs;ljflkas;jdlflkhsadf",
                *       master: 'malk',
                *   }],
                * }
                * */
                RoomResponse roomResponse = new RoomResponse();
                roomResponse.setResponseType("createRoom");
                roomResponse.setMemberList(memberList);
                roomResponse.setRoomList(roomList);

                session.getAsyncRemote().sendText(objectMapper.writeValueAsString(roomResponse));
            }

        }else if(requestType.equals("enterRoom")) {
            String userId = jsonNode.get("userId").asText();
            String roomName = jsonNode.get("roomName").asText();
            Member member = (Member)session.getUserProperties().get("member");

            //방 고유 식별자
            UUID uuid = UUID.randomUUID();
            Room room = new Room();
            room.setUUID(uuid.toString());
            room.setMaster(userId);
            room.setRoomName(roomName);

            //참가자
            Set users = new HashSet();
            Member obj = new Member();
            obj.setId(member.getId());
            obj.setName(member.getName());
            obj.setEmail(member.getEmail());
            users.add(obj);
            room.setUsers(users);
            roomList.add(room);

            //응답 프로토콜
            RoomResponse roomResponse = new RoomResponse();
            roomResponse.setResponseType("enterRoom");
            roomResponse.setMemberList(memberList);
            roomResponse.setRoomList(roomList);

            session.getAsyncRemote().sendText(objectMapper.writeValueAsString(roomResponse));

        }else if(requestType.equals("exitRoom")) {

        }else if(requestType.equals("chat")) {

        }
    }
}