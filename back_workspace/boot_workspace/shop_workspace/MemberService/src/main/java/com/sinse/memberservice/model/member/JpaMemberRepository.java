package com.sinse.memberservice.model.member;

import com.sinse.memberservice.domain.Member;
import com.sinse.memberservice.domain.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMemberRepository extends JpaRepository<Member, String> {
    public Member findByProviderAndId(Provider provider, String id);
}
