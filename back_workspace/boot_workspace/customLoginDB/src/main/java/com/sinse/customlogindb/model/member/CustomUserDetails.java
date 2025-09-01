package com.sinse.customlogindb.model.member;

import com.sinse.customlogindb.domain.Member;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/*로그인 검증이 성공되면 Authentication 객체가 생성되고, 이 객체에 UserDetails 객체가 등록된다
    로그인에 성공한 사용자 정보를 보유한다.
    스프링 시큐리티 프레임워크는 회원정보를 담은 객체가 어떤 클래스인지 예측할 수 없기 때문에
    UserDetails 라는 이름의 인터페이스를 미리 정해놓았고, 개발자가 이 인터페이스의 메서드에 회원정보를 채워넣기를 원한다.
    그래야 개발자 대신 id, password 이용하여 db 검증이 가능*/
@Data
public class CustomUserDetails implements UserDetails {
    private Member member;

    //생성자 주입
    public CustomUserDetails(Member member) {
        this.member = member;
    }

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
