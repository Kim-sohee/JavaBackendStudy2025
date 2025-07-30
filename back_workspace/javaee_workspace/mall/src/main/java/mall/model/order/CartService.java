package mall.model.order;

import mall.domain.Cart;

public interface CartService {
	public void regist(Cart cart);		//DB뿐 아니라 기타 업무를 수행할 수도 있기 때문에.. insert라는 명칭으로 업무를 국한시키지 말자
}
