package com.sinse.chatroomapp.controller;

import com.sinse.chatroomapp.domain.Member;
import com.sinse.chatroomapp.model.member.MemberService;
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
    public String login(Member member, Model model){
        Member m = memberService.login(member);
        if(m!=null){
            return "member/main";
        }

        model.addAttribute("loginFail", true);
        return "member/login";
    }
}
