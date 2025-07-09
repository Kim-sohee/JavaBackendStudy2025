package com.sinse.mvcapp.color.model;

/*MVC 패턴에 의해, 디자인 영역과 로직 및 데이터 영역 분리시켜야 유지보수성이 높아지기 때문에*/
public class BloodManager {
	public String getAdvice(String blood) {
		String msg = "";
		if(blood.equals("A")){
			msg = "신중하고 꼼꼼함";
		}else if(blood.equals("B")){
			msg = "자유롭고 개성이 강함";
		}else if(blood.equals("O")){
			msg = "외향적이고 리더쉽이 있음";
		}else if(blood.equals("AB")){
			msg = "이성적이고 독특함";
		}
		return msg;
	}
}
