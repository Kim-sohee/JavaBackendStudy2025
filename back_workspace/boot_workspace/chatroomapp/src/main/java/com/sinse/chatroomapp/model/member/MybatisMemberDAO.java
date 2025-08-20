package com.sinse.chatroomapp.model.member;

import com.sinse.chatroomapp.domain.Member;
import com.sinse.chatroomapp.exception.MemberException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MybatisMemberDAO implements MemberDAO {

    private MemberMapper memberMapper;

    public MybatisMemberDAO(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public List<Member> selectAll() {
        return memberMapper.selectAll();
    }

    @Override
    public Member select(int id) {
        return memberMapper.select(id);
    }

    @Override
    public Member login(Member member) {
        return memberMapper.login(member);
    }

    @Override
    public void insert(Member member) {
        memberMapper.insert(member);
    }

    @Override
    public void update(Member member) {
        memberMapper.update(member);
    }

    @Override
    public void delete(int id) {
        memberMapper.delete(id);
    }
}
