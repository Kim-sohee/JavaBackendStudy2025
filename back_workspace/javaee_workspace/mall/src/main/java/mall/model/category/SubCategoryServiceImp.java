package mall.model.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import mall.domain.SubCategory;

@Service
public class SubCategoryServiceImp implements SubCategoryService{

	//DAO를 느슨하게 보유
	@Autowired
	SubCategoryDAO subCategoryDAO;
	
	public List selectAll() {
		return subCategoryDAO.selectAll();
	}

	@Override
	public List selectByTopCategoryId(int topcategory_id) {
		return subCategoryDAO.selectByTopCategoryId(topcategory_id);
	}

}
