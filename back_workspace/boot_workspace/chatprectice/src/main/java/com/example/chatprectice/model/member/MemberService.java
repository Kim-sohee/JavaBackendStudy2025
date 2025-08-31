package com.example.chatprectice.model.member;

import com.example.chatprectice.domain.Member;
import com.example.chatprectice.exception.MemberException;

import java.util.List;

public interface MemberService {
    public List<Member> selectAll();
    public Member select(int member_id);
    public Member login(Member member);
    public void insert(Member member);
    public void update(Member member);
    public void delete(int member_id);
}
