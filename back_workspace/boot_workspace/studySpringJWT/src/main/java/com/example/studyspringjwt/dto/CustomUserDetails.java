package com.example.studyspringjwt.dto;

import com.example.studyspringjwt.entity.UserEntity;
import com.example.studyspringjwt.model.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

//사용자 정보를 인증하고 권한을 부여하는 데 필요한 정보를 제공하는 객체
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final UserEntity userEntity;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {    //사용자 권한
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return userEntity.getRole();        //Admin, User 와 같은 역할 설정
            }
        });
        return collection;
    }

    @Override
    public String getPassword() {       //사용자 비밀번호 반환, Security 인증 시 이 비밀번호와 입력된 비밀번호를 비교
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {   //사용자 이름을 반환, Security 인증 시 이 이름과 입력된 이름을 비교
        return userEntity.getUsername();
    }

    //아래는 계정 상태 관련 메서드들이다.(계정이 만료되었는지, 계정이 잠겼는지, 비밀번호가 만료됐는지, 계정이 활성화되어 있는지 확인)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
