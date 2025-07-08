package com.sinse.hiberasync.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UploadServlet extends HttpServlet{
	Logger logger = LoggerFactory.getLogger(getClass());
	String uploadPath;
	
	//이 서블릿의 인스턴스가 생성될 때, 서블릿의 초기화를 담당하는 메서드인 init을 이용하면,
	//개발자가 이 서블릿의 원하는 정보를 전달할 수 있다.
	//필터와 동일하다!
	@Override
	public void init(ServletConfig config) throws ServletException {
		uploadPath =config.getServletContext().getRealPath(config.getInitParameter("uploadPath"));
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uploadPath = this.getServletContext().getRealPath("/data");	//jsp 내장객체(request, response, out, session, application) 
		logger.debug("저장할 실제 경로는" + uploadPath);
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
		
		//클라이언트의 요청을 분석(파싱)
		try {
			List<FileItem> list = servletFileUpload.parseRequest(request);
			logger.debug("전송된 컴포넌트의 수는 "+list.size());
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}
}
