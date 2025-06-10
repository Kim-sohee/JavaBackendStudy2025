package com.sinse.networkapp.backup;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//접속한 클라이언트의 메시지를 그대로 보내주는 에코서버를 구축해본다.
public class EchoServer {
	ServerSocket server;	//소켓이라는 단어가 포함되어 있지만, 대화용 소켓이 아니라 어떤 유저가 접속하는지를 감지하는 접속 감지용 서버
	
	public EchoServer() {
		try {
			server = new ServerSocket(9999);
			Socket socket = server.accept();	//접속자가 발생할 때까지 무한 대기
			String ip = socket.getInetAddress().getHostAddress();
			
			System.out.println(ip + "접속 발견");
			
			//소켓을 통해 데이터를 주고 받을 수 있는 스트림을 얻자
			//방향에 따라 입력과 출력으로 나누어 짐, 데이터 처리 방식에 따라 바이트(~stream), 문자(~reader, writer), 버퍼로 나누어짐
			InputStream is = socket.getInputStream();		//바이트 기반의 입력 스트림을 얻어옴
			OutputStream os = socket.getOutputStream();	//바이트 기반의 출력 스트림
			
			//무한 루프는 엄청난 속도이므로, 프로그램에서 사용시 주의해야 하지만
			//스트림 처리에서는 read() 메서드 자체가 상대방의 메시지를 받을 때까지 대기상태에 빠지게 되므로 부하X
			while(true) {				
				int data = is.read();		//1byte 읽어들임
				System.out.println((char)data);
				os.write(data); 				//읽어들인 1바이트 데이터를 그대로 보내버림(출력)
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new EchoServer();
	}
}
