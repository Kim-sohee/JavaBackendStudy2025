package com.sinse.networkapp.unicast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

//접속자마다 1:1 대응하여 인스턴스가 생성될 대화용 쓰레드
//대화가 가능하려면, 입력과 출력 스트림이 필요하다.
public class ServerThread extends Thread{
	GUIServer guiServer;
	Socket socket;
	BufferedReader buffr;
	BufferedWriter buffw;
	
	//소켓을 서버로부터 전달받으면 된다. 접속자가 들어올 때마다
	public ServerThread(GUIServer guiServer,Socket socket) {
		this.socket = socket;
		this.guiServer = guiServer;
		try {
			//접속과 동시에 스트림을 얻어놓아야 대화가 가능
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	//듣기
	public void listen() {
		String msg = null;
		try {
			msg = buffr.readLine();
			guiServer.area.append(msg+"\n");
			send(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//말하기
	public void send(String msg) {
		try {
			buffw.write(msg+"\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(true) {
			listen();
		}
	}
}
