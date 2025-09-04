package com.sinse.jwtlogin.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

//스프링 시큐리티는 회원 정보를 가진 객체를 알 수 없으므로,
//시큐리티가 미리 정해놓은 UserDetails안으로 Member 내용을 옮겨둬야 한다.
public class CustomUserDetails implements UserDetails {
    private Member member;

    //생성자 주입
    public CustomUserDetails(Member member) {
        this.member = member;
    }

    //권한 목록을 나열
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getId();
    }
}
