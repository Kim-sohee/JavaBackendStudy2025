package mall.model.member;

import mall.domain.Member;

public interface MemberService {
	public Member checkDuplicate(String id);
	public void regist(Member member);
}
