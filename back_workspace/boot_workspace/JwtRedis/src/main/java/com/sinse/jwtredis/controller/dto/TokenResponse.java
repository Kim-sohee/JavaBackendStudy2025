package com.sinse.jwtredis.controller.dto;

import lombok.Data;

//토큰을 클라이언트에게 전송할 때, json으로 자동으로 변환시키기 위한 DTO
@Data
public class TokenResponse {
    private String accessToken;
    private long expireSec;

    public TokenResponse(String accessToken, long expireSec) {
        this.accessToken = accessToken;
        this.expireSec = expireSec;
    }
}
