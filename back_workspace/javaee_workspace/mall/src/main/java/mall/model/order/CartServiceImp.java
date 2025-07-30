package mall.model.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mall.domain.Cart;
import mall.exception.CartException;

@Service
public class CartServiceImp implements CartService{

	@Autowired
	private CartDAO cartDAO;
	
	@Override
	public void regist(Cart cart) throws CartException {
		cartDAO.insert(cart);
	}
	
}
