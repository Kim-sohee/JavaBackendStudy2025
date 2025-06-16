package com.sinse.networkapp.multicast;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUIServer extends JFrame{
	JPanel p_north;
	JTextField t_port;
	JButton bt;
	JTextArea area;
	JScrollPane scroll;
	Thread thread;	//서버 가동용 스레드(메인 쓰레드가 대기에 빠지지 않기 위해 필요)
	
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
		
		setSize(300, 400);
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
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new GUIServer();
	}
}
