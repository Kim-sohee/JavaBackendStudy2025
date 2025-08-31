package com.example.chatprectice.controller;

import com.example.chatprectice.domain.Member;
import com.example.chatprectice.model.member.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class MemberController {
    private MemberService memberService;
    //생성자 주입
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/member/loginform")
    public String loginForm(){
        return "member/login";
    }

    @PostMapping("member/login")
    @ResponseBody
    public ResponseEntity<String> login(Member member, HttpSession session){
        Member obj = memberService.login(member);
        session.setAttribute("member", obj);

        return ResponseEntity.ok("success");
    }

    @GetMapping("/chat/main")
    public String main(HttpSession session) {
        String viewName="chat/main";
        if(session.getAttribute("member") == null) {
            viewName="member/login";
        }
        return viewName;
    }
}
