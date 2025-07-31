package mymodel2.controller.color;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mymodel2.model.color.ColorService;

//모델 1 방식에서는 여전히 뷰 역할의 jsp가 controller 코드도 포함하고 있기 때문에 
//만일 디자인 파일을 다른 파일로 교체할 경우, controller도 함께 소실되므로 유지보수에 문제 발생
//따라서 뷰와 컨트롤러가 합쳐진 jsp에서 controller를 분리시키자. 

/*javaEE에서의 컨트롤러의 조건
 * 1) javaEE 웹 컨테이너에서 실행되어야 한다. -> 웹을 이해할 수 있어야 함
 * 2) Java 기술을 이해해야 한다.
 * 결론: 서블릿 밖에 없다.
 * */
public class ColorController extends HttpServlet {
	 ColorService colorService = new ColorService();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//클라이언트의 요청을 받자
		String color = request.getParameter("color");

		String msg = colorService.getAdvice(color);
		
		//컨트롤러는 순수하게 컨트롤러로써 역할만 수행해야 하므로, 절대 모델의 역할을 수행하거나 디자인의 View의 역할을 수행해서는 안된다.
		//따라서 여기서 out.print()를 출력해서는 안되며 대신 View에서 사용할 데이터를 저장해놓기
		
		//요청 객체에 데이터를 담는다는 것은, 절대로 응답을 하지 않고 요청을 유지하겠다는 것임
		//뷰 페이지의 요청이 끝날때까지..
		request.setAttribute("msg", msg);
		RequestDispatcher dis = request.getRequestDispatcher("/color/color_form.jsp");
		dis.forward(request, response);
	}
}
