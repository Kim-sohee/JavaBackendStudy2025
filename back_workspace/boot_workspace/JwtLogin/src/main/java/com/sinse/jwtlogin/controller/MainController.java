package com.sinse.jwtlogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    //스프링 시큐리티의 기본으로 제공되는 폼 로그인은, 로그인 인증 성공 후 무조건 /(루트)로 리다이렉트하기 때문에
    //이에 대한 요청을 처리해야 함
    @GetMapping("/")
    public String index(){
        return "index.html";
    }
}
