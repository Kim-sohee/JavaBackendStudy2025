package com.sinse.chatroomapp.model.member;

import com.sinse.chatroomapp.domain.Member;
import com.sinse.chatroomapp.exception.MemberException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImp implements MemberService {
    private MemberDAO memberDAO;

    public MemberServiceImp(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    @Override
    public List<Member> selectAll() {
        return memberDAO.selectAll();
    }

    @Override
    public Member select(int id) {
        return memberDAO.select(id);
    }

    @Override
    public Member login(Member member) {
        return memberDAO.login(member);
    }

    @Override
    public void insert(Member member) throws MemberException {
        try {
            memberDAO.insert(member);
        } catch (DataAccessException e) {
            throw new MemberException("수정 실패", e);
        }
    }

    @Override
    public void update(Member member) throws MemberException {
        try {
            memberDAO.update(member);
        } catch (DataAccessException e) {
            throw new MemberException("글 등록 실패", e);
        }
    }

    @Override
    public void delete(int id) throws MemberException {
        try {
            memberDAO.delete(id);
        } catch (DataAccessException e) {
            throw new MemberException("삭제 실패", e);
        }
    }
}
