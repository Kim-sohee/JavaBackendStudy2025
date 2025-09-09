package com.sinse.jwtredis.model.member;

import com.sinse.jwtredis.controller.dto.MemberDTO;
import org.springframework.stereotype.Service;

@Service
public class RegistServiceImp implements RegistService {
    private final RegistRedisService registRedisService;

    public RegistServiceImp(RegistRedisService registRedisService) {
        this.registRedisService = registRedisService;
    }

    @Override
    public void regist(MemberDTO memberDTO) {
        String code = registRedisService.generateCode6();
        memberDTO.setCode(code);

        //임시 회원정보를 redis에 등록
        //registRedisService.savePending(memberDTO);

    }

}
