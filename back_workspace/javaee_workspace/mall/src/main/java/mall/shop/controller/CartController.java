package mall.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;
import mall.domain.Cart;
import mall.model.category.TopCategoryService;

@Slf4j
@Controller
public class CartController {
	
	//장바구니 목록 요청
	@GetMapping("/cart/list")
	public String getList(Model model) {
		return "shop/order/cart_list";
	}
	
	
	//장바구니 담기
	@PostMapping("/cart/regist")
	public String regist(@ModelAttribute Cart cart) {
		log.debug("product_id= "+cart.getProduct().getProduct_id());
		log.debug("ea= "+cart.getEa());
		log.debug("member_id= "+cart.getMember().getMember_id());
		log.debug("selectedColor= "+cart.getColor().getColor_id());
		log.debug("selectedSize= "+cart.getSize().getSize_id());
		
		return null;
	}
}
