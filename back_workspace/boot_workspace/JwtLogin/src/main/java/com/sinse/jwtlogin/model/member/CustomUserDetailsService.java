package com.sinse.jwtlogin.model.member;

import com.sinse.jwtlogin.domain.CustomUserDetails;
import com.sinse.jwtlogin.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//스프링 시큐리티에서는 개발자가 로그인 검증을 위한 서비스 객체를 별도로 정의할 필요 없고
//UserDetailsService를 재정의하여 구현하면 된다.
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final JpaMemberRepository jpaMemberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //데이터베이스에서 해당 유저명으로 객체를 조회
        Member member = jpaMemberRepository.findById(username);
        if(member == null){
            throw new UsernameNotFoundException("로그인 정보가 올바르지 않습니다.");
        }

        //하지만 코드에는 보이지 않지만, 내부적으로 DaoAuthenticationProvider가 비밀번호 검증을 스스로 수행함

        return new CustomUserDetails(member);
    }
}
