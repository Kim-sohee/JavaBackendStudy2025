package com.sinse.jwtredis.model.member;

import com.sinse.jwtredis.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMemberRepository extends JpaRepository<Member,String> {
    public Member save(Member member);

    public Member findMemberById(String id);
}
