package mall.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	//@GetMapping		//RequesetMapping(value="", method=RequestMethod.GET)과 같음
	@RequestMapping(value="/admin/main", method=RequestMethod.GET)
	public ModelAndView getMain() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("secure/index");
		return mav;
	}
}
