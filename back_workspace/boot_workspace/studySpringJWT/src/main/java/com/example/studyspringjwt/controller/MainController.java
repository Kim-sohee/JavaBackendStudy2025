package com.example.studyspringjwt.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.Iterator;

@Controller
@ResponseBody
public class MainController {
    @GetMapping("/")
    public String main(){
        //JWT Filter 통과 후 세션 확인

        //세션 현재 사용자 아이디
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        //세션 현재 사용자 role
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority grantedAuthority = iterator.next();
        String role = grantedAuthority.getAuthority();

        return "[Main] username: "+username+" role: "+role;
    }
}
