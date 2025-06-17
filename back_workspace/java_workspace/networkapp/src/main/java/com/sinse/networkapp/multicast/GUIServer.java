package com.sinse.networkapp.multicast;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sinse.networkapp.unicast.ServerThread;

public class GUIServer extends JFrame{
	JPanel p_north;
	JTextField t_port;
	JButton bt;
	JTextArea area;
	JScrollPane scroll;
	Thread thread;	//서버 가동용 스레드(메인 쓰레드가 대기에 빠지지 않기 위해 필요)
	
	//ArrayList도 가능은 하지만 다중 쓰레드 환경에서 쓰레드간의 동기화를 지원하지 않으므로,
	//운이 없다면, ArrayList 하나의 인덱스에 동시에 쓰레드가 접근하게 되는 상황이 발생할 수 있다.
	//이 경우 개발자가 Syncronized{} 블럭으로 코드를 감싸면, 특정 쓰레드가 해당 블럭을 실행하는 동안 다른 쓰레드는 대기상태에 걸려서 동기로 실행 가능
	//Vector는 이미 동기화 처리가 되어 있다.
	Vector<ServerChatThread> vec = new Vector<>();
	
	public GUIServer() {
		p_north = new JPanel();
		t_port = new JTextField("9999", 8);
		bt = new JButton("서버 가동");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		
		//스타일
		p_north.setPreferredSize(new Dimension(300, 40));
		p_north.setBackground(Color.LIGHT_GRAY);
		t_port.setPreferredSize(new Dimension(150, 30));
		
		area.setBackground(Color.yellow);
		
		// 조립
		p_north.add(t_port);
		p_north.add(bt);
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		
		bt.addActionListener(e->{
			thread = new Thread(()->{
				startServer();
			});
			thread.start();
		});
		
		setSize(300, 530);
		setVisible(true);
	}
	
	public void startServer() {
		int port = Integer.parseInt(t_port.getText());
		try {
			ServerSocket server = new ServerSocket(port);
			area.append("서버 생성 및 접속자 감지 시작\n");
			
			while(true) {
				Socket socket = server.accept();		//접속자가 감지될 때까지 무한대기에 있다가, 접속자가 발견되면 대화용 소켓이 반환되면서 대기상태 풀림
				String ip = socket.getInetAddress().getHostAddress();
				area.append(ip+" 님 접속 감지\n");
				
				ServerChatThread chatThread = new ServerChatThread(this, socket);
				chatThread.start();		//쓰레드 동작 시작
				
				//현재 서버에 접속한 클라이언트 정보인 ServerChatThread를 Vector에 넣는다.
				vec.add(chatThread);
				area.append("현재 "+vec.size()+"명이 접속\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new GUIServer();
	}
}
