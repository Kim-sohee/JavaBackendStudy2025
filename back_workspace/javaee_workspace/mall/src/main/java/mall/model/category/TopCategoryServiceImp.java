package mall.model.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mall.domain.TopCategory;

@Service
public class TopCategoryServiceImp implements TopCategoryService{

	//DAO를 느슨하게 보유
	@Qualifier("hibernateTopCategoryDAO")
	@Autowired
	TopCategoryDAO topCategoryDAO; 
	
	@Transactional
	public List selectAll() {
		return topCategoryDAO.selectAll();
	}

	@Override
	public TopCategory select(int topcategory_id) {
		return null;
	}

}
