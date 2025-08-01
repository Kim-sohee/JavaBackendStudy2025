package mymodel2.web.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import mymodel2.web.context.support.ApplicationContext;

//톰켓이 가동될 때를 감지하는 리스너를 정의
public class ContextLoaderListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//전역 객체에 스프링 컨테이너 추가하기
		ServletContext context = sce.getServletContext();	//전역 객체 얻기!
		String contextClass = context.getInitParameter("contextClass");
		System.out.println("설정 파일로부터 넘겨받은 빈 컨테이너의 경로는 "+contextClass);
		
		// 현재로서는 String인 경로이므로, 이 경로를 이용하여 실제 클래스화 시키자!
		try {
			Class contextClazz = Class.forName(contextClass);	//클래스 형식
			//이거 왜 생성했을까? -> 전역 객체에게 선물로 주려고!
			ApplicationContext applicationContext = (ApplicationContext)contextClazz.newInstance();		//컨테이너의 인스턴스 생성
			String realPath = context.getRealPath(context.getInitParameter("contextConfigLocation"));
			System.out.println(realPath);
			applicationContext.init(realPath);
			context.setAttribute("applicationContext", applicationContext);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
	
}
