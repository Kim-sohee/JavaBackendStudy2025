package mall.model.member;

import java.util.List;

import mall.domain.SnsProvider;

public interface SnsProviderDAO {
	public List selectAll();
	public SnsProvider selectByName(String name);
}
