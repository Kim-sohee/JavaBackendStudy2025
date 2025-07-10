package mvcproject.color.model;

public class ColorManager {
	public String getAdvice(String color) {
		String msg = null;
		if(color.equals("red")){
			msg = "열정적이고 활동적";
		}else if(color.equals("blue")){
			msg = "신중하고 분석적";
		}else if(color.equals("yellow")){
			msg = "낙천적이고 외향적";
		}else if(color.equals("green")){
			msg = "온화하고 배려심이 깊음";
		}
		return msg;
	}
}
