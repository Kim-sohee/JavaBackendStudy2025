package imagecloud;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

//클라이언트가 전송한 텍스트 데이터, 바이너리 데이터까지 처리해보기
public class UploadServlet extends HttpServlet{
	String savePath = "C:/dev/lecture_space/back_workspace/javaee_workspace/imagecloud/src/main/webapp/public";	//바이너리 파일이 저장될 서버의 경로
	int maxLimit = 3 * 1024 * 1024;	//3M
	
	//클라이언트가 상당히 많은 양의 바이너리로 요청을 시도하므로, post로 전송하기 때문에 doPost로 처리한다.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글깨짐 처리(인코딩)
		request.setCharacterEncoding("utf-8"); 	//전송되어 지는 파라미터의 값들이 깨지지 않도록 인코딩 지정
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		//업로드 처리
		try {
			MultipartRequest multi = new MultipartRequest(request, savePath, maxLimit, "utf-8");
			System.out.println("업로드 성공");
			
			//텍스트 파라미터 추출
			//파일 업로드 컴포넌트를 이용하면 파라미터 추출 마저도 업로드 컴포넌트를 이용
			String title = multi.getParameter("title");
			System.out.println("전송된 제목은 "+title);
			out.print("전송된 제목은 "+title);
			
			//파일 처리 관련
			//1)이미 서버에 저장되어버린 이미지를 개발자가 원하는 이미지로 교체
			//2)클라이언트 측에서 파일명을 결정짓고 업로드하면 이 과정은 불필요
			
			/*
			 * collection framework란?
			 * - 객체를 모아서 처리할 때, 효율적으로 처리할 수 있도록 지원되는 java util 패키지에서 지원하는 api
			 * 
			 * 1) 순서 있는 모음: 대표적인 모습은 배열이지만, 배열은 기본 자료형도 제어하므로(객체만을 다루진 않음) 컬렉션 프레임웍은 아니다.
			 * 						ex) List
			 * 2) 순서 없는 모음
			 * 						ex) Set, Map<Key,Value>
			 * 
			 * 순서 없는 컬렉션 객체를 처리할 때(일렬로 늘어뜨림) 사용되는 도구가 2가지 유형이 있다. (Iterator, Enumeration)
			 * 그 중 Enumeration 제어해본다.
			 */
			Enumeration<String> en = multi.getFileNames();
			while(en.hasMoreElements()) {
				String param=en.nextElement();
//				out.print("업로드 된 파라미터 명은 "+param);
				
				//파라미터 명을 이용하면, 업로드된 파일명도 추출 가능
				String filename = multi.getOriginalFileName(param);
				System.out.println("업로드 된 파일명은 " +filename);	//클라이언트가 브라우저가 아닐때
				out.print("업로드 된 파일명은 " +filename);		//클라이언트가 브라우저일 때
			}
			
		} catch (IOException e) {
			System.out.println("업로드 실패, 용량 등을 확인하세요.");
			e.printStackTrace();
		}
	}
}
