package com.example.studyspringjwt.service;

import com.example.studyspringjwt.dto.CustomUserDetails;
import com.example.studyspringjwt.entity.UserEntity;
import com.example.studyspringjwt.model.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
//Spring Security의 로그인 인증 절차를 사용하려면 반드시 UserDetailService를 구현하거나 등록해야 한다.
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //DB조회
        UserEntity userEntity = userRepository.findByUsername(username);

        if(userEntity != null){
            return new CustomUserDetails(userEntity);
        }
        return null;
    }
}
