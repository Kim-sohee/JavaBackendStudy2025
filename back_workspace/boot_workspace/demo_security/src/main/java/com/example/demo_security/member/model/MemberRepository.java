package com.example.demo_security.member.model;

import com.example.demo_security.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    //아이디로 유저 한명 가져오기
    public Member findById(String id);
}
