package com.sinse.jwtredis.controller;

import com.sinse.jwtredis.controller.dto.MemberDTO;
import com.sinse.jwtredis.domain.CustomUserDetails;
import com.sinse.jwtredis.domain.Member;
import com.sinse.jwtredis.model.member.MemberService;
import com.sinse.jwtredis.model.member.RedisTokenService;
import com.sinse.jwtredis.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final AuthenticationManager authenticationManager;

    //JWT 관련
    private final JwtUtil jwtUtil;
    private final RedisTokenService redisTokenService;

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
    //발급받은 JWT가 없다면, 인증 후 JWT 발급
    @PostMapping("/member/login")
    public ResponseEntity<?> login(@RequestBody  MemberDTO memberDTO) {
        log.debug("개발자 정의 컨트롤러 로그인 요청 받음");

        //유효한 JWT를 보유했는지 여부를 먼저 따져보기
        //따라서 db에 회원이 존재하는지 여부를 판단..
        Member member = new Member();
        member.setId(memberDTO.getId());
        member.setPassword(memberDTO.getPwd());

        //인증 시도
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(member.getId(), member.getPassword())
        );

        CustomUserDetails userDetails = (CustomUserDetails)authentication.getPrincipal();
        log.debug("인증받은 회원의 아이디는 "+ userDetails.getUsername());
        log.debug("인증받은 회원의 이메일은 "+ userDetails.getEmail());
        log.debug("인증받은 회원의 권한은 "+ userDetails.getRoleName());

        //인증에 성공하면 AccessToken, RefreshToken 검증

        //사용자 전역(모든 디바이스를 섭렵) 토큰 버전 가져오기
        int userVersion = redisTokenService.currentUserVersion(userDetails.getUsername());

        //토큰 발급
        //참고) 원래 디바이스 아이디는 디바이스마다 고유해야 하므로, UUID를 적극 활용하자
        String accessToken = jwtUtil.createAccessToken(userDetails.getUsername(), userVersion, memberDTO.getDeviceId());
        String refreshToken = jwtUtil.createRefreshToken(userDetails.getUsername(), memberDTO.getDeviceId());



        return ResponseEntity.ok("로그인 성공");
    }
}
