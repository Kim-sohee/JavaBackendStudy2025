package com.sinse.memberservice.model.member;

import com.sinse.memberservice.domain.Member;
import com.sinse.memberservice.domain.Provider;
import com.sinse.memberservice.domain.Role;
import com.sinse.memberservice.util.UserInfoExtractor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

/* 시큐리티의 OAuth2 프레임워크를 이용하면 Provider에게 토큰을 받는 과정까지 Security가 자동으로 해줌
*   개발자는 토큰이 이미 전달된 시점부터 관여하면 된다.
*
*  해야할 업무
*  이미 회원가입 하였는지 여부를 판단히가에 적절한 컬럼은 id, provider_id(google=1, naver=2)이므로,
*  1) Provider를 조회
*  2) Provider가 유저에게 할당한 고유 ID
*  3) DB 회원이 없다면 자동 가입
*  2)
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final JpaProviderRepository providerRepository;
    private final JpaMemberRepository memberRepository;
    private final JpaRoleRepository roleRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        //1) Provider 유형 얻기
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        log.debug("당신이 가입한 프로바이더는 {}", registrationId);

        Provider provider = providerRepository.findByProviderName(registrationId);
        if(provider == null){
            //시스템에서 지원하지 않는 프로바이더인 경우 -> 로그인 실패 예외
            throw new OAuth2AuthenticationException("Unknown provider: " + registrationId);
        }

        //2) 회원 정보 꺼내기
        OAuth2User user = super.loadUser(userRequest);
        Map<String, Object> attr = user.getAttributes();
        log.debug("유저 정보는 {}", attr);

        //이미 회원인지 아닌지 판단
        //회원이 아니면 -> 강제가입, 회원이면? 로그인 처리(혹시 이메일, 닉네임이 변경되었다면 최신으로 수정)
        String providerId = UserInfoExtractor.getProviderId(registrationId, attr);  //sns에서 부여한 고유 id
        String email = UserInfoExtractor.getEmail(registrationId, attr);
        String name = UserInfoExtractor.getName(registrationId, attr);

        Member existing = memberRepository.findByProviderAndId(provider, providerId);

        //SNS 검증은 거쳤으나 DB 회원이 아니라면 자동가입
        Member member = null;
        if(existing == null){
            member = new Member();
            member.setProvider(provider);
            member.setEmail(email);
            member.setName(name);
            member.setId(providerId);

            Role role = roleRepository.findByRoleName("USER");
            member.setRole(role);
            memberRepository.save(member);
        }else{
            //DB 회원이라면
            member = existing;

            //attr에서 꺼내온 이메일이 현재 우리 db의 이메일 정보와 다른 경우..
            if(email != null && !email.equals(member.getEmail())){
                member.setEmail(email);     //최신 이메일로 업데이트
            }
            if(name != null && !name.equals(member.getName())){
               member.setName(name); //최신 정보로 이름 또는 nickname 업데이트
            }
            memberRepository.save(member);

        }

        return user;
    }
}
