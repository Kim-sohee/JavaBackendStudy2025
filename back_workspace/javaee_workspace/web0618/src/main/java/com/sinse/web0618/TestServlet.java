package com.sinse.web0618;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//서블릿: 자바의 클래스 중 오직 JavaEE 기반의 서버에서만 해석 및 실행될 수 있는 클래스
/*서블릿은 개발자가 인스턴스를 생성하는 방법이 아니라 그냥 클래스 작성 후 서버에 올려놓으면 된다.
 * 그 후 최초 요청에 의해 인스턴스가 한 번 올라간다.(by tomcat)
 * 서블릿이 언제 태어나고, 어떤 일을 수행하며, 언제 소멸하는지에 대한 하나의 주기를 서블릿의 lifecycle이라 한다.*/

/*서블릿의 생명주기 관련 메서드
 * 1) init(): 서블릿이 태어날 때, 초기화를 위해 호출되는 메서드 (call by tomcat)
 * 2) service(): 서블릿이 클라이언트의 모든 요청을 처리할 때 호출되는 메서드(call by thread)
 * 3) destory(): 서블릿이 소멸될 때 호출되는 메서드 (call by tomcat)*/
public class TestServlet extends HttpServlet{
	
	//init() 생성된 이후에 init()이 호출되기 때문에 생성자보다 호출이 늦다.
	public void init() throws ServletException {
		System.out.println("init()호출: 제가 누군지 알게 되었어요.");
	}
	
	//서블릿은 클라이언트의 요청을 처리하기 위해 태어났으므로, 이 요청 처리를 담당하는 메서드가 바로 service()
	//고객의 요청을 처리하려면 뭘 원하는지에 대한 요청 정보와 그 요청에 대한 응답정보를 가지고 있어야 한다.
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("service()호출: 요청을 처리합니다.");
	}
	
	//서블릿이 소멸하는 단계에서 호출되는 메서드
	private void destory() {
		System.out.println("destory()호출: 저 갑니다. 여기서 무언가 다 반납할게요.");
	}
}
