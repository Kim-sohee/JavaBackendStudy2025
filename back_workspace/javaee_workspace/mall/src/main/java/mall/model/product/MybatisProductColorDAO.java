package mall.model.product;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mall.domain.ProductColor;
import mall.exception.ProductColorException;
import mall.exception.ProductException;

@Repository
public class MybatisProductColorDAO implements ProductColorDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public void insert(ProductColor productColor) throws ProductColorException{
		int result = sqlSessionTemplate.insert("ProductColor.insert", productColor);
		if(result < 1) {
			throw new ProductException("상품의 지원 색상 등록 실패");
		}
	}
	
}
