package myframework.staff.model.service;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import myframework.mybatis.MybatisConfig;
import myframework.staff.model.domain.Bio;
import myframework.staff.model.repository.BioDAO;
import myframework.staff.model.repository.StaffDAO;

//서비스란? 모델 파트의 업무를 추상화(단순화) 시켜 컨트롤러가 상세히 모델 파트의 업무를 수행하지 않아도 되도록 존재
//만일 이 서비스 객체가 없다면, 컨트롤러가 모델 파트의 전문 영역을 담당해야 하는 상황 발생
//이 경우, 컨트롤러와 모델 영역의 경계가 모호해지므로, 추후 컨트롤러가 교체되면 모델이 동작하지 않게 된다.
public class StaffService {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	//두 개의 DAO를 트랜잭션으로 묶기 위해 SqlSession을 공유하기 위해
	MybatisConfig config = MybatisConfig.getInstance();
	StaffDAO staffDAO = new StaffDAO();
	BioDAO bioDAO = new BioDAO();
	
	//CURD
	public void regist(Bio bio) {
		//두 개의 업무를 여기서 수행
		SqlSession sqlSession = config.getSqlSession();		
		try {
			logger.debug("사원 등록 전의 staff"+bio.getStaff().getStaff_id());
			staffDAO.insert(sqlSession, bio.getStaff());
			
			logger.debug("사원 등록 후의 staff"+bio.getStaff().getStaff_id());
			bioDAO.insert(sqlSession, bio);
			
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		}finally {
			sqlSession.close();
		}
	}
}
