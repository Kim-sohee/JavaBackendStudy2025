package com.example.demo_security.security.model;

import com.example.demo_security.member.domain.Member;
import com.example.demo_security.member.model.MemberRepository;
import com.example.demo_security.security.dto.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //DB조회
        Member member = memberRepository.findById(username);

        if(member != null){
            return new CustomUserDetails(member);
        }
        return null;
    }
}
