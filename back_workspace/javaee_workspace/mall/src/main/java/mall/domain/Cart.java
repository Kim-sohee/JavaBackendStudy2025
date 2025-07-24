package mall.domain;

import lombok.Data;

@Data
public class Cart {
	private int cart_id;
	private Product product;
	private int ea;
	private int member_id;
	
	//선택한 색상
	private String selectedColor;
	//선택한 사이즈
	private String selectedSize;
}
