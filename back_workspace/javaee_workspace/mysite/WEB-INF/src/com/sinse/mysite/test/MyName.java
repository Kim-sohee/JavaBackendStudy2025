package com.sinse.mysite.test;

//java�� Ŭ������ �̿��Ͽ�, ���� �̸��� ���������� ����غ���
//�ڹ��� Ŭ���� ��, ���� javaEE ����� ���������� �ؼ� �� ����� �� �ִ� Ŭ������ ������ ����(Servlet)�̶� �Ѵ�.
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.ServletException;
import java.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSerletResponse;
import java.io.PrintWriter;

public class MyName extends HttpServlet{	/*�������� �����Ϸ���, ������ ��ӹ޾ƾ� �Ѵ�.*/
	//������ �����ڰ� ���Ƿ� �޼��带 �����ϴ� ���� �ƴ϶�, �̹� ������ �޼��尡 �ִ�.
	
	//�θ��� ������ doGet�� �������̵� ����
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//�� �������� ������ html ������ �ۼ��غ���.
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();	//���������� ����� ��Ʈ�� ���
		out.print("this is my first app!!");
		
	}
}