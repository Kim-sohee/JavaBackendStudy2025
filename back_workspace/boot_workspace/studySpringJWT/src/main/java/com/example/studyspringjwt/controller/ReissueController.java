package com.example.studyspringjwt.controller;

import com.example.studyspringjwt.dto.TokenResponseDTO;
import com.example.studyspringjwt.exception.JwtRefreshTokenException;
import com.example.studyspringjwt.service.ReissueService;
import com.example.studyspringjwt.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReissueController {
    private final ReissueService reissueService;

    //Refresh 토큰으로 새 Access 토큰 발급
    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response){
        try{
            TokenResponseDTO tokenDTO = reissueService.reissueAccessToken(request);

            //헤더 + 쿠키 설정
            response.setHeader("access", tokenDTO.getAccessToken());
            response.addCookie(CookieUtil.createCookie("refresh", tokenDTO.getRefreshToken()));

            return ResponseEntity.ok().build();
        }catch(JwtRefreshTokenException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
