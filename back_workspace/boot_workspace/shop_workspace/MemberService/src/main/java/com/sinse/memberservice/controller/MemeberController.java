package com.sinse.memberservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/memberapp")
public class MemeberController {

    @GetMapping("/members")
    public ResponseEntity<?> getMembers() {
        return ResponseEntity.ok(Map.of("result","회원목록 입니다"));
    }

    //OAuth2 인증 시스템이 한바퀴 돌아가는지 테스트
    @GetMapping("/login/ok")
    public String loginOk(){
        return "Login ok";
    }
}





