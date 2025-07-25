package mall.model.member;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mall.domain.SnsProvider;

@Repository
public class MybatisSnsProviderDAO implements SnsProviderDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectAll() {
		return sqlSessionTemplate.selectList("SnsProvider.selectAll");
	}
	
	@Override
	public SnsProvider selectByName(String name) {
		return sqlSessionTemplate.selectOne("SnsProvider.selectByName", name);
	}

}
