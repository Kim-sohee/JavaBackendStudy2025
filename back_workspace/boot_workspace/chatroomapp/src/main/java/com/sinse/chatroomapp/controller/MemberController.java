package com.sinse.chatroomapp.controller;

import com.sinse.chatroomapp.domain.Member;
import com.sinse.chatroomapp.model.member.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MemberController {
    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/member/loginform")
    public String loginForm(){
        return "member/login";
    }

    @PostMapping("/member/login")
    public String login(Member member, Model model, HttpSession session){
        Member m = memberService.login(member);
        if(m!=null){
            session.setAttribute("member", m);
            return "redirect:/chat/main";
        }

        model.addAttribute("loginFail", true);
        return "member/login";
    }

    @GetMapping("/chat/main")
    public String main(HttpSession session){
        String viewName = "chat/main";
        //로그인하지 않고, 해당 경로에 접근하면 자동으로 로그인 폼으로 돌려보냄
        if(session.getAttribute("member") == null){
            viewName = "member/login";
        }
        return viewName;
    }

    @GetMapping("/chat/room")
    public String room(){
        return "chat/room";
    }
}
