package com.sinse.customlogindb.model.member;

import com.sinse.customlogindb.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaMemberDAO implements MemberDAO {
    private final JpaMemberRepository jpaMemberRepository;

    @Override
    public List<Member> findAll() {
        return List.of();
    }

    @Override
    public Member findById(String member_id) {
        return null;
    }

    @Override
    public void insert(Member member) {

    }

    @Override
    public void update(Member member) {

    }

    @Override
    public void delete(Member member) {

    }

    @Override
    public Member getMemberById(String member_id) {
        return jpaMemberRepository.findById(member_id);
    }
}
