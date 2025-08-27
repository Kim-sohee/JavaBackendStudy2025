package com.sinse.electroshop.model.member;

import com.sinse.electroshop.domain.Member;
import com.sinse.electroshop.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberDAO memberDAO;

    @Override
    public List<Member> findAll() {
        return List.of();
    }

    @Override
    public Member findById(int member_id) {
        return null;
    }

    @Override
    public Member login(Member member) throws MemberNotFoundException {
        Member obj = memberDAO.findLogin(member);
        if(obj == null){
            //일치하는 정보가 없음
            throw new MemberNotFoundException("로그인 정보가 일치하지 않습니다.");
        }
        return obj;
    }

    @Override
    public Member save(Member member) {
        return null;
    }

    @Override
    public Member update(Member member) {
        return null;
    }

    @Override
    public void delete(int member_id) {

    }
}
