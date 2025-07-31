package mall.model.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mall.domain.SnsProvider;

@Service
public class SnsProviderServiceImp implements SnsProviderService {
	@Autowired
	private SnsProviderDAO snsProviderDAO;
	
	@Override
	public List selectAll() {
		return null;
	}
	
	@Override
	public SnsProvider selectByName(String name) {
		return snsProviderDAO.selectByName(name);
	}
}
