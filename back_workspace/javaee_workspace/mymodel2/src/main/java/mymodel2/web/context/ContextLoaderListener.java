package mymodel2.web.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//톰켓이 가동될 때를 감지하는 리스너를 정의
public class ContextLoaderListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//ServletContext는 앱과 생명을 같이하는 객체이므로, 톰켓이 가동될 때 이 객체를 이용할 일이 있다면
		//contextInitialized() 메서드에서 처리한다.
		ServletContext servletContext = sce.getServletContext();
		String contextClass = servletContext.getInitParameter("contextClass");		//mymodel2.web.context.support.ApplicationContext
		String contextConfigLocation = servletContext.getInitParameter("contextConfigLocation");
		
		System.out.println(contextConfigLocation);

		try {
			Class contextClazz = Class.forName(contextClass);	//지정된 경로의 클래스를 실제 클래스로 로드
			Object contextInstance = contextClazz.newInstance();		//인스턴스 생성
			
			//ServletContext 사용할 ApplicationContext(Bean 컨테이너)를 전달하자.
			servletContext.setAttribute("applicationContext", servletContext);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
	
}
