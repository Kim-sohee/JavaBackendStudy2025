package com.sinse.jwtredis.model.member;

import com.sinse.jwtredis.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImp implements MemberService {

    private final JpaMemberRepository jpaMemberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void regist(Member member) throws RuntimeException {
        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        Member obj = jpaMemberRepository.save(member);
        if(obj==null) throw new RuntimeException("등록 실패!");
    }
}
