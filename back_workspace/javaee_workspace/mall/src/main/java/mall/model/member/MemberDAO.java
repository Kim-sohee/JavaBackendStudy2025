package mall.model.member;

import mall.domain.Member;

public interface MemberDAO {
	public Member checkDuplicate(String id);
	public void insert(Member member);
}
