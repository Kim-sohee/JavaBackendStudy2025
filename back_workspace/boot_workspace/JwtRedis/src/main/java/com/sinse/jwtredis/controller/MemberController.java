package com.sinse.jwtredis.controller;

import com.sinse.jwtredis.controller.dto.MemberDTO;
import com.sinse.jwtredis.domain.CustomUserDetails;
import com.sinse.jwtredis.domain.Member;
import com.sinse.jwtredis.model.member.MemberService;
import com.sinse.jwtredis.model.member.RegistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/member/regist")
    public ResponseEntity<?> regist(@RequestBody MemberDTO memberDTO) {
        log.debug("member={}", memberDTO);

        Member member = new Member();
        member.setId(memberDTO.getId());
        member.setPassword(memberDTO.getPwd());
        member.setName(memberDTO.getName());
        member.setEmail(memberDTO.getEmail());

        memberService.regist(member);

        return ResponseEntity.ok("가입성공");
    }

    //로그인 요청 처리
    @PostMapping("/member/login")
    public ResponseEntity<?> login(@RequestBody  MemberDTO memberDTO) {
        log.debug("개발자 정의 컨트롤러 로그인 요청 받음");

        Member member = new Member();
        member.setId(memberDTO.getId());
        member.setPassword(memberDTO.getPwd());

        //인증 시도
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(member.getId(), member.getPassword())
        );

        //인증에 성공하면 AccessToken, RefreshToken 생성
        CustomUserDetails userDetails = (CustomUserDetails)authentication.getPrincipal();
        log.debug("인증받은 회원의 아이디는 "+ userDetails.getUsername());
        log.debug("인증받은 회원의 이메일은 "+ userDetails.getEmail());
        log.debug("인증받은 회원의 권한은 "+ userDetails.getRoleName());

        return ResponseEntity.ok("로그인 성공");
    }
}
