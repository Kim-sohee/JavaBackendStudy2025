package mall.notice.model;

import java.util.List;

import mall.domain.Notice;

public interface NoticeService {
	public List selectAll();
	public Notice select();
	
	//두개 이상의 DAO를 대상으로 업무할 경우 insert보다는 상위 개념의 등록을 표현하는 메서드명으로 가는게 좋다.
	public void regist(Notice notice);
	public void update(Notice notice);
	public void delete(int notice_id);
}
