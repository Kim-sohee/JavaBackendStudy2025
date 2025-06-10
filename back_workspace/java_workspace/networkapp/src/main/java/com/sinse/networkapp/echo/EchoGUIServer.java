package com.sinse.networkapp.echo;

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

public class EchoGUIServer extends JFrame{
	JPanel p_north;
	JTextField t_port;
	JButton bt;
	JTextArea area;
	JScrollPane scroll;
	ServerSocket server;
	Thread thread;	//메인 쓰레드가 대기상태에 빠지지 않도록 쓰레드로 accept 처리
	
	public EchoGUIServer() {
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
			thread = new Thread() {
				public void run() {					
					runServer();
				}
			};
			
			thread.start();
		});
		
		setSize(300, 400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void runServer() {
		int port = Integer.parseInt(t_port.getText());
		try {
			server = new ServerSocket(port);
			area.append("서버 생성 및 접속자 청취중..\n");
			Socket socket = server.accept();		//접속자 기다리기, 접속이 발견되면 소켓반환
			String ip = socket.getInetAddress().getHostAddress();
			area.append(ip+"접속자 발견\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//우리가 실행부라고 알고 있었던 존재가 사실 메인 쓰레드라 불리는 프로그램 운영 쓰레드이다.
	/*프로그램을 운영하는 메인쓰레드는 다음과 같은 작업을 금기시 한다.
	1) 무한루프, 2) 대기상태 : accept, read ...
		메인 쓰레드를 대기상태에 빠뜨리면 이벤트 감지, GUI 그래픽 처리 불가, 모든 것이 멈춘다. 
	*/
	public static void main(String[] args) {
		new EchoGUIServer();
	}
}
