package mall.model.member;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mall.domain.Member;
import mall.exception.MemberException;

@Repository
public class MybatisMemberDAO implements MemberDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public Member checkDuplicate(String id) throws MemberException {
		Member member = (Member)sqlSessionTemplate.selectOne("Member.checkDuplicate", id);
		if(member != null) {	//중복된 회원 발견
			throw new MemberException("중복된 아이디 발견");
		}
		return member;
	}

	@Override
	public void insert(Member member) throws MemberException{
		int result = sqlSessionTemplate.insert("Member.insert", member);
		if(result<1) {
			throw new MemberException("회원 등록 실패");
		}
	}

}
