package mall.domain;

import lombok.Data;

@Data
public class Cart {
	private int cart_id;
	private Product product;
	private int ea;
	private Member member;
	
	//선택한 색상
	private Color color;
	//선택한 사이즈
	private Size size;
}
