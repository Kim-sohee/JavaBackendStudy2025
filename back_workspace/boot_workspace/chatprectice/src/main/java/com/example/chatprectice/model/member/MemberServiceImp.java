package com.example.chatprectice.model.member;

import com.example.chatprectice.domain.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImp implements MemberService {

    private MemberDAO memberDAO;
    //생성자 주입
    public MemberServiceImp(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    @Override
    public List<Member> selectAll() {
        return memberDAO.selectAll();
    }

    @Override
    public Member select(int member_id) {
        return memberDAO.select(member_id);
    }

    @Override
    public Member login(Member member) {
        return memberDAO.login(member);
    }

    @Override
    public void insert(Member member) {
        memberDAO.insert(member);
    }

    @Override
    public void update(Member member) {
        memberDAO.update(member);
    }

    @Override
    public void delete(int member_id) {
        memberDAO.delete(member_id);
    }
}
