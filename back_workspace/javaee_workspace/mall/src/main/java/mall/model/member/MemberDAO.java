package mall.model.member;

import mall.domain.Member;

public interface MemberDAO {
	public Member selectById(String id);
	public void insert(Member member);
}
