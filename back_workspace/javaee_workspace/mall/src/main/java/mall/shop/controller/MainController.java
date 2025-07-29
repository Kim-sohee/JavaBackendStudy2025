package mall.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import mall.model.category.TopCategoryService;

@Slf4j
@Controller
public class MainController {
	
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public ModelAndView getMain() {
		ModelAndView mav = new ModelAndView("shop/index");
		return mav;
	}
}
