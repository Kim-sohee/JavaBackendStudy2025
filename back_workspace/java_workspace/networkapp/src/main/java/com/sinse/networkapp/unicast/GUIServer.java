package com.sinse.networkapp.unicast;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

public class GUIServer extends JFrame implements Runnable{
	JPanel p_north;
	JTextField t_port;
	JButton bt;
	JTextArea area;
	JScrollPane scroll;
	Thread thread;
	ServerSocket server;
	
	//사용자가 접속할 때마다, 몇 명이 현재 서버를 사용중인지, 그 기록을 처리할 객체
	Vector<ServerThread> vec = new Vector<>();
	
	public GUIServer() {
		p_north = new JPanel();
		t_port = new JTextField("9999", 8);
		bt = new JButton("서버 가동");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		
		area.setBackground(Color.YELLOW);
		
		p_north.add(t_port);
		p_north.add(bt);
		add(p_north, BorderLayout.NORTH);
		add(scroll);
	
		bt.addActionListener(e->{
			thread = new Thread(GUIServer.this);	//runnable 구현자를 대입하면, 쓰레드 start 시 구현자의 run 호출
			thread.start();
		});
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		setBounds(500, 300, 300,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void startServer() {
		try {
			server = new ServerSocket(Integer.parseInt(t_port.getText()));
			area.append("서버 생성 및 접속자 청취 중..\n");
			while(true) {
				Socket socket = server.accept();		//대기상태에 빠지므로 쓰레드로 처리함
				area.append(socket.getInetAddress()+" 님 접속!\n");
				
				//접속자 1명당, 대화용 쓰레드인 serverThread 인스턴스를 만들면서 socket을 선물로 주자.
				ServerThread st = new ServerThread(this, socket);
				st.start();
				vec.add(st);		//접속자 추가
				area.append("현재 접속자: "+vec.size()+"명\n");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		startServer();
	}
	
	public static void main(String[] args) {
		new GUIServer();
	}
}
