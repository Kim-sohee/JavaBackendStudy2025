package mymodel2.model.color;

public class ColorService {

	public String getAdvice(String color) {
		//파라미터에 대한 판단
		if(color == null) color="";
		
		String msg = null;
		if(color.equals("red")){
			msg="열정적이고 목표 지향적임, 추진력이 강함";
		}else if(color.equals("blue")){
			msg = "감성적이고 배려심이 깊으며 관계를 중요시 함";
		}else if(color.equals("yellow")){
			msg = "명랑하고 낙천적이며 창의적임";
		}else if(color.equals("green")){
			msg = "차분하고 분석적이며 신중함";
		}else if(color.equals("black")){
			msg = "철학적임";
		}else{
			msg = "선택한 색상이 없네요.";
		}
		
		return msg;
	}
}
