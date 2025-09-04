package com.sinse.jwtlogin.controller;

import com.sinse.jwtlogin.domain.CustomUserDetails;
import com.sinse.jwtlogin.model.member.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/member/login")
    public ResponseEntity<?> login(String username, String password){
        log.debug("로그인 요청 받음!");

        CustomUserDetails customUserDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(username);

        //추출한 user의 비밀번호와 파라미터로 넘어온 유저의 비번을 비교
        if(customUserDetails != null && passwordEncoder.matches(password, customUserDetails.getPassword())) {
            //로그인 성공하였으므로, JWT 발급

        }

        return null;
    }
}
