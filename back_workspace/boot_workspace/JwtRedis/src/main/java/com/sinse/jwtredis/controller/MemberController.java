package com.sinse.jwtredis.controller;

import com.sinse.jwtredis.controller.dto.MemberDTO;
import com.sinse.jwtredis.controller.dto.TokenResponse;
import com.sinse.jwtredis.domain.CustomUserDetails;
import com.sinse.jwtredis.domain.Member;
import com.sinse.jwtredis.model.member.MemberService;
import com.sinse.jwtredis.model.member.RedisTokenService;
import com.sinse.jwtredis.util.CookieUtil;
import com.sinse.jwtredis.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MemberController {
    private final MemberService memberService;
    private final AuthenticationManager authenticationManager;

    //JWT 관련
    private final JwtUtil jwtUtil;
    private final RedisTokenService redisTokenService;

    private final long accessMinutes;
    private final long refreshDays;

    public MemberController(MemberService memberService,
            AuthenticationManager authenticationManager,
            JwtUtil jwtUtil,
            RedisTokenService redisTokenService,
            @Value("${app.jwt.access-minutes}") long accessMinutes,
            @Value("${app.jwt.refresh-days}")long refreshDays){
        this.memberService = memberService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.redisTokenService = redisTokenService;

        this.accessMinutes = accessMinutes;
        this.refreshDays = refreshDays;
    }

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
    public ResponseEntity<?> login(@RequestBody  MemberDTO memberDTO, HttpServletResponse response) {
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

        //refresh token을 보안 쿠키에 담기
        long rfTtlSec = refreshDays * (24*60*60);
        CookieUtil.setRefreshCookie(response, refreshToken, (int)rfTtlSec);

        //엑세스 토큰의 유효시간
        long expSec = jwtUtil.parseToken(accessToken).getBody().getExpiration().toInstant().getEpochSecond();

        return ResponseEntity.ok(new TokenResponse(accessToken, expSec));
    }
}
