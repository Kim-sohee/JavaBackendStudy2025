package mall.admin.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import mall.domain.Color;
import mall.domain.Product;
import mall.domain.ProductColor;
import mall.domain.ProductSize;
import mall.domain.Size;
import mall.model.category.TopCategoryService;
import mall.model.product.ProductService;

@Slf4j
@Controller
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	//서비스에게 일시킴 (느슨하게 보유, 즉 결합도 낮추어서 보유, 따라서 인터페이스로 보유)
	@Autowired
	private TopCategoryService topCategoryService;
	
	//localhost:8888/admin/admin/product/registform
	@RequestMapping(value="/admin/product/registform")
	public String registform() {
		
		return "secure/product/regist";
	}
	
	//상품 등록 요청을 처리
	@PostMapping("/admin/product/regist")
	@ResponseBody
	public String regist(Product product, int[] color, int[] size, MultipartFile[] photo,HttpServletRequest request) {
		//MultipartFile 변수와 html 이름이 동일하면 매핑된다.
		log.debug("업로드한 파일의 수는 "+photo.length);
		
		//Product 객체의 멤버변수로 직접 html과 매핑이 될 수 없는 경우엔, 우회하면 된다.
		//우회란? 파라미터를 별도로 받아서, 다시 product에 넣어준다.
		//사용자가 선택한 색상이 3개라면, ProductColor의 인스턴스도 3개를 생성!
		//스프링의 자동 매핑이 불가하므로, 개발자가 직접 생성
		List<ProductColor> colorList = new ArrayList<>();
		List<ProductSize> sizeList = new ArrayList<>();
		
		for(int c : color) {
			Color cc = new Color();
			cc.setColor_id(c);
			
			ProductColor productColor = new ProductColor();
			//유저가 선택한 색상 대입
			productColor.setColor(cc);
			colorList.add(productColor);
		}
		
		for(int s: size) {
			Size ss = new Size();
			ss.setSize_id(s);
			
			ProductSize productSize = new ProductSize();
			productSize.setSize(ss);
			sizeList.add(productSize);
		}
		
		//매핑완료 후, Product에 대입
		product.setColorList(colorList);
		product.setSizeList(sizeList);
		
		
		//모델 객체는 table을 반영한 객체이므로 컨트롤러 영역에서 바로 파라미터를 받는 용도로 사용해선 안된다.
		//왜? 데이터베이스 컬럼명이 노출되기 때문,
		//해결책: 클라이언트의 파라미터를 받는 용도의 객체를 별도로 둔다(DTO = Data Transfer Object)
		//DTO에서 Model 객체로 옮겨야 한다.
		
		productService.regist(product);
		
//		log.debug("product= "+product);
//		ServletContext context = request.getServletContext();		//jsp application 내장 객체, 애플리케이션과 생명을 같이 함
//		String realPath = context.getRealPath("/data");
//		log.debug("realPath is "+realPath);
		
		//4단계: DML은 저장할 게 없다.
		return "ok";
	}
}
