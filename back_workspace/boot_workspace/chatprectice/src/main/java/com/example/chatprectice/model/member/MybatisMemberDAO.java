package com.example.chatprectice.model.member;

import com.example.chatprectice.domain.Member;
import com.example.chatprectice.exception.MemberException;
import jdk.jfr.Registered;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class MybatisMemberDAO implements MemberDAO {
    private MemberMapper memberMapper;

    //생성자 주입
    public MybatisMemberDAO(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public List<Member> selectAll() {
        return memberMapper.selectAll();
    }

    @Override
    public Member select(int member_id) {
        return memberMapper.select(member_id);
    }

    @Override
    public Member login(Member member) {
        return memberMapper.login(member);
    }

    @Override
    public void insert(Member member) throws MemberException {
        try {
            memberMapper.insert(member);
        } catch (DataAccessException e) {
            throw new MemberException(e);
        }
    }

    @Override
    public void update(Member member) throws MemberException {
        try {
            memberMapper.update(member);
        } catch (DataAccessException e) {
            throw new MemberException(e);
        }
    }

    @Override
    public void delete(int member_id) throws MemberException {
        try {
            memberMapper.delete(member_id);
        } catch (DataAccessException e) {
            throw new MemberException(e);
        }
    }
}
