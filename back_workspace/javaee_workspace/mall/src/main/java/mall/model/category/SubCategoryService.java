package mall.model.category;

import java.util.List;

import mall.domain.SubCategory;

public interface SubCategoryService {
	public List selectAll();
	public List selectByTopCategoryId(int topcategory_id);
}
