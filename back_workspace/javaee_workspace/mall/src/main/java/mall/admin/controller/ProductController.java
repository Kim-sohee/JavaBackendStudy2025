package mall.admin.controller;


import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import mall.domain.Product;
import mall.model.category.TopCategoryService;

@Slf4j
@Controller
public class ProductController {
	
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
	public String regist(Product product, HttpServletRequest request) {
		//MultipartFile 변수와 html 이름이 동일하면 매핑된다.
		
		//모델 객체는 table을 반영한 객체이므로 컨트롤러 영역에서 바로 파라미터를 받는 용도로 사용해선 안된다.
		//왜? 데이터베이스 컬럼명이 노출되기 때문,
		//해결책: 클라이언트의 파라미터를 받는 용도의 객체를 별도로 둔다(DTO = Data Transfer Object)
		//DTO에서 Model 객체로 옮겨야 한다.
		
//		log.debug("product= "+product);
//		ServletContext context = request.getServletContext();		//jsp application 내장 객체, 애플리케이션과 생명을 같이 함
//		String realPath = context.getRealPath("/data");
//		log.debug("realPath is "+realPath);
		
		//4단계: DML은 저장할 게 없다.
		return "redirect:/admin/admin/product/list";
	}
}
