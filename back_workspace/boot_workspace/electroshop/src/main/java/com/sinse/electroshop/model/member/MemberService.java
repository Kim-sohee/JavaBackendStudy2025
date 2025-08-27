package com.sinse.electroshop.model.member;

import com.sinse.electroshop.domain.Member;

import java.util.List;

public interface MemberService {
    //모든 회원 가져오기
    public List<Member> findAll();

    //회원 한명 가져오기
    public Member findById(int member_id);

    //로그인 회원 가져오기
    public Member login(Member member);

    //회원 등록
    public Member save(Member member);

    //회원 정보 수정
    public Member update(Member member);

    //회원 정보 삭제
    public void delete(int member_id);
}
