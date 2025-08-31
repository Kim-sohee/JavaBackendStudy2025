package com.example.studyspringjwt.service;

import com.example.studyspringjwt.dto.JoinDTO;
import com.example.studyspringjwt.entity.UserEntity;
import com.example.studyspringjwt.model.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    //회원가입
    public void joinProcess(JoinDTO joinDTO) {
        String username = joinDTO.getUsername();
        String password = joinDTO.getPassword();

        Boolean isExist = userRepository.existsByUsername(username);
        if (isExist) {
            //이미 존재한다면?
            return;
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(bCryptPasswordEncoder.encode(password));     //비밀번호 암호화
        userEntity.setRole("ROLE_ADMIN");

        userRepository.save(userEntity);
    }
}
