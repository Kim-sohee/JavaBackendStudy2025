package mall.shop.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import mall.domain.Cart;
import mall.domain.Member;
import mall.exception.CartException;
import mall.model.order.CartService;

@Slf4j
@Controller
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	//장바구니 목록 요청
	@GetMapping("/cart/list")
	public String getList(Model model) {
		return "shop/order/cart_list";
	}
	
	
	//장바구니 담기
	@PostMapping("/cart/regist")
	@ResponseBody		//jsp 응답을 막고, json 기타 데이터 타입으로 응답 정보 구성
	public String regist(@ModelAttribute Cart cart, HttpSession session) {
		Member member = (Member)session.getAttribute("member");
		cart.setMember(member);		//세션에서 얻어온 모델 대입
		
		log.debug("product_id= "+cart.getProduct().getProduct_id());
		log.debug("ea= "+cart.getEa());
		log.debug("member_id= "+member.getMember_id());
		log.debug("selectedColor= "+cart.getColor().getColor_id());
		log.debug("selectedSize= "+cart.getSize().getSize_id());
		
		cartService.regist(cart);
		
		return "ok";
	}
	
}
