package fileserver;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

public class UploadServlet extends HttpServlet{
	//클라이언트의 요청이 Post 방식이므로, doPost() 재정의
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		out.print("Post 요청 받음");
		
		//파일 업로드 컴포넌트 중 cos.jar 사용
		int maxLimit = 3*1024*1024;	//3M
		MultipartRequest multi = new MultipartRequest(request, 
				"C:\\dev\\lecture_space\\back_workspace\\javaee_workspace\\fileserver\\src\\main\\webapp\\data",
				maxLimit,
				"utf-8");
		
		
	}
}
