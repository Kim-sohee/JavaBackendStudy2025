package mall.model.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SizeServiceImp implements SizeService{
	@Autowired
	private SizeDAO sizeDAO;
	
	@Override
	public List selectAll() {
		return sizeDAO.selectAll();
	}

}
