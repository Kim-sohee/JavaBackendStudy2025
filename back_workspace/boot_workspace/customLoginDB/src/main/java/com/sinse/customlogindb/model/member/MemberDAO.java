package com.sinse.customlogindb.model.member;

import com.sinse.customlogindb.domain.Member;

import java.util.List;

public interface MemberDAO {
    public List<Member> findAll();
    public Member findById(String member_id);

    public void insert(Member member);
    public void update(Member member);
    public void delete(Member member);

    //로그인 정보 조회
    public Member getMemberById(String member_id);
}
