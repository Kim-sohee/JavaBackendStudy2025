package com.sinse.electroshop.model.member;

import com.sinse.electroshop.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class JpaMemberDAO implements  MemberDAO{

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public List<Member> findAll() {
        return memberJpaRepository.findAll();
    }

    @Override
    public Member findById(int member_id) {
        return memberJpaRepository.findById(member_id).orElse(null);
    }

    @Override
    public Member findLogin(Member member) {
        return memberJpaRepository.findByIdAndPassword(member.getId(), member.getPassword());
    }

    @Override
    public Member save(Member member) {
        return memberJpaRepository.save(member);
    }

    @Override
    public Member update(Member member) {
        return memberJpaRepository.save(member);
    }

    @Override
    public void delete(int member_id) {
        memberJpaRepository.deleteById(member_id);
    }
}
