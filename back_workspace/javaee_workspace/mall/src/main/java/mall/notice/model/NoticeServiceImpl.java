package mall.notice.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import mall.domain.Notice;
import mall.exception.NoticeException;

@Slf4j
@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Qualifier("hibernateNoticeDAO")
//	@Qualifier("mybatisNoticeDAO")		//스프링 컨테이너가 보유한 여러 인스턴스 중 원하는 아이디를 넣어야 한다
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Transactional
	public List selectAll() {
		log.debug("service의 selectAll() 도달");
		return noticeDAO.selectAll();		//DAO 메서드 호출
	}

	@Override
	public Notice select() {
		return null;
	}

	@Transactional
	public void regist(Notice notice) throws NoticeException {
		noticeDAO.insert(notice);		//다형성으로 동작한다.
	}

	@Override
	public void update(Notice notice) {
	}

	@Override
	public void delete(int notice_id) {
	}
	
}
