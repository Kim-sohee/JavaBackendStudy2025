package com.sinse.chatroomapp.model.chat;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinse.chatroomapp.domain.Member;
import com.sinse.chatroomapp.dto.*;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.*;
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
            CreateRoomResponse createRoomResponse = new CreateRoomResponse();
            createRoomResponse.setResponseType("createRoom");

            //회원 정보 채우기
            Member obj = new Member();
            obj.setId(member.getId());
            obj.setName(member.getName());
            obj.setEmail(member.getEmail());
            memberList.add(obj);

            createRoomResponse.setMemberList(memberList);

            String json = objectMapper.writeValueAsString(createRoomResponse);
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
                room.setUserList(users);

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
                CreateRoomResponse createRoomResponse = new CreateRoomResponse();
                createRoomResponse.setResponseType("createRoom");
                createRoomResponse.setMemberList(memberList);
                createRoomResponse.setRoomList(roomList);

                //방이 생성된 사실을 서버에 접속한 모든 클라이언트가 알아야 하므로, 브로드캐스트의 대상이 된다.
                for(Session ss:userList){
                    ss.getAsyncRemote().sendText(objectMapper.writeValueAsString(createRoomResponse));
                }
            }

        }else if(requestType.equals("enterRoom")) {
            log.debug("방입장 처리 요청");
            /*1) 요청한 클라이언트를 선택한 방에 밀어넣기
            *       - 넘겨받은 uuid를 이용하여 방 선택
            *       - 해당 Room이 보유한 Set에 클라이언트를 참여자 등록(중복을 피함)
            */
            String uuid =  jsonNode.get("uuid").asText();

            //클라이언트가 전송한 uuid를 이용하여 모든 방을 탐색한 후, uuid가 일치하는 방을 선택
            Room room = null;
            for(Room r : roomList) {
                if(r.getUUID().equals(uuid)) {
                    room = r;
                    break;
                }
            }
            /*
             * 선언적 프로그래밍 방식으로도 위의 작업을 진행할 수 있다.
            roomList.stream()
                    .filter(r -> r.getUUID().equals(uuid))      //조건에 맞는 요소만 추림
                    .findFirst()    //조건에 맞는 첫번째 조건
                    .orElse(null);      //없으면 null 리턴
             */

            //찾아낸 Room 안에 채팅 참여자로 등록(등록되어 있지 않은 사람만..)
            //현재 클라이언트와 연결된 session에 담겨진 회원 정보를 추출
            Member member = (Member)session.getUserProperties().get("member");

            //룸에 들어있는 유저들 정보와 비교하여 같지 않은 경우에만 유저를 방에 추가
            boolean exists = false;
            for(Member obj : room.getUserList()) {
                if(member.getId().equals(obj.getId())) {
                    exists = true;  //중복 발견
                    break;
                }
            }
            //룸에 등록되어 있지 않다면..
            Member obj = new Member();  //이 멤버가 곧 클라이언트에게 전송될 예정이므로, 보안 상 중요한 부분은 제외시키기 위해 별도의 Member 선언
            if(exists==false) {
                obj.setId(member.getId());
                obj.setName(member.getName());
                obj.setEmail(member.getEmail());
                room.getUserList().add(obj);    //채팅방 참여자로 등록
            }

            /*
            * {
            *       responseType: "enterRoom",
            *       room: { ... },
            *
            * }
            * */
            //응답 정보 만들기
            EnterRoomResponse enterRoomResponse = new EnterRoomResponse();
            enterRoomResponse.setResponseType("enterRoom");
            enterRoomResponse.setRoom(room);

            session.getAsyncRemote().sendText(objectMapper.writeValueAsString(enterRoomResponse));

        }else if(requestType.equals("chat")) {
            log.debug("채팅 요청 받음");

            String sender = jsonNode.get("sender").asText();
            String data =  jsonNode.get("data").asText();
            String uuid = jsonNode.get("uuid").asText();

            /*1) 같은 방에 있는 유저들에게 브로드캐스팅 한다.
                    -클라이언트가 전송한 uuid를 이용하여, 서버에 존재하는 여러 채팅방 중 한 방을 선택하자.
            * */
            Room room = null;
            for(Room r :roomList){
                if(uuid.equals(r.getUUID())) {
                    room = r;
                    break;
                }
            }

            //Room에 들어있는 대화 참여자들의 정보를 이용하여, Session을 보유한 userList와 비교한 후 대화 참여자의 Session이 발견되면
            //메시지를 보내자(브로드캐스팅)
            /*
            * {
            *       responseType: "chat",
            *       sender: 발화자,
            *       data: 메시지,
            *       uuid: uuid
            * }*/
            //전송 메시지 구성하기
            ChatResponse chatResponse = new ChatResponse();
            chatResponse.setResponseType("chat");
            chatResponse.setSender(sender);
            chatResponse.setData(data);
            chatResponse.setUuid(uuid);
            String json = objectMapper.writeValueAsString(chatResponse);

            for(Session ss :userList) {     //전체 접속자를 대상으로..
                for(Member m: room.getUserList()) {     //지정된 방에 참여한 참여자를 대상으로
                        Member obj = (Member)ss.getUserProperties().get("member");      //웹 소켓 Session에 심어놓은 Member를 꺼내자
                        if(obj.getId().equals(m.getId())) {
                            ss.getAsyncRemote().sendText(json);
                        }
                }
            }

        }else if(requestType.equals("exitRoom")) {

        }
    }

    @OnClose
    public void onClose(Session session) throws Exception {
        /*
        Member member = (Member)session.getUserProperties().get("member");

        //3가지 목록에서 사용자 제거
        userList.remove(session);
        memberList.remove(member);

        //현재 접속자가 참여하고 있었던 그 방에서 빼야 함
        //roomList에서 Room을 선택하여, 해당 Room이 보유한 Set에서 현재 Member 제거
        Room room = null;
        Member mr = null;
        for(Room r : roomList) {
            for(Member obj : r.getUserList()) {
                if(obj.getId().equals(member.getId())) {
                    room = r;
                    mr = obj;
                    break;
                }
            }
        }
        room.getUserList().remove(mr);

        //클라이언트에게 전송할 정보 구성 후, 보내기
        // 응답 정보 구성하여 전송
        CloseResponse closeResponse = new CloseResponse();
        closeResponse.setResponseType("close");
        closeResponse.setMemberList(memberList);
        closeResponse.setRoomList(roomList);

        String json = objectMapper.writeValueAsString(closeResponse);
        session.getAsyncRemote().sendText(json);
     */
    }

}