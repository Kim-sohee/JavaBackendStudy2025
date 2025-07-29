package mall.model.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mall.domain.Member;
import mall.exception.MemberException;
import mall.exception.MemberNotFoundException;
import mall.exception.PasswordEncryptException;
import mall.util.PasswordUtil;

@Slf4j
@Service
public class MemberServiceImp implements MemberService {

	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private PasswordUtil passwordUtil;
	
	@Override
	public Member selectById(String id) throws MemberException {
		return memberDAO.selectById(id);
	}

	@Override
	public void regist(Member member) throws PasswordEncryptException {
		log.debug("sns provider is "+member.getSnsProvider());
		
		if(member.getSnsProvider() == null) {
			//홈페이지 회원인 경우
			String salt = passwordUtil.generateSalt();
			
			//전송된 파라미터와 salt를 섞어서 해시 생성
			String hashedPassword = passwordUtil.hashPassword(member.getPassword(), salt);
			
			//Member 모델에, 덮어쓰기
			member.setSalt(salt);
			member.setPassword(hashedPassword);
			
		}
		memberDAO.insert(member);			
		
		//이메일 발송
	}

	@Override
	public Member login(Member member) throws MemberException, MemberNotFoundException{
		//이메일을 이용하여, 현재 로그인 시도 회원이 과거에 가입 시 사용했던 salt를 조사해본다.
		Member obj = memberDAO.selectByEmail(member.getEmail());
		
		log.debug("db에 저장된 salt는 "+obj.getSalt());
		log.debug("db에 저장된 salt와 파라미터로 전송된 비번과의 조합 해시는 "+passwordUtil.hashPassword(member.getPassword(), obj.getSalt()));
		log.debug("db에 저장되어 있었던 비번은 "+obj.getPassword());
		
		String dbHash = passwordUtil.hashPassword(member.getPassword(), obj.getSalt());
		
		if(dbHash.equals(obj.getPassword())==false) {
			throw new MemberNotFoundException("로그인 정보가 올바르지 않습니다.");
		}
		return obj;
	}

}
