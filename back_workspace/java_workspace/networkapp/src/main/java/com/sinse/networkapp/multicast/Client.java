package com.sinse.networkapp.multicast;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client extends JFrame implements Runnable{
	JPanel p_north;
	JComboBox cb_ip;
	JTextField t_port;
	JButton bt_con;
	
	JTextArea area;
	JScrollPane scroll;
	JTextField t_input;
	Thread thread;		//접속 쓰레드
	ClientChatThread chatThread;		//채팅용 쓰레드
	
	public Client() {
		//생성
		p_north = new JPanel();
		cb_ip = new JComboBox();
		t_port = new JTextField("9999", 8);
		bt_con = new JButton("연결");
		
		area = new JTextArea();
		scroll = new JScrollPane(area);
		t_input = new JTextField();
		thread = new Thread(this);
		
		//스타일
		p_north.setPreferredSize(new Dimension(300, 40));
		p_north.setBackground(Color.LIGHT_GRAY);
		cb_ip.setPreferredSize(new Dimension(100, 30));
		t_port.setPreferredSize(new Dimension(100, 30));
		
		area.setPreferredSize(new Dimension(290, 400));
		area.setBackground(Color.yellow);
		t_input.setPreferredSize(new Dimension(290, 30));
		
		//조립
		p_north.add(cb_ip);
		p_north.add(t_port);
		p_north.add(bt_con);
		add(p_north, BorderLayout.NORTH);
		
		add(scroll);
		add(t_input, BorderLayout.SOUTH);
		
		bt_con.addActionListener(e->{
			thread.start();
		});
		
		t_input.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					chatThread.send(t_input.getText());
					t_input.setText("");
				}
			}
		});
		
		//이벤트
		cb_ip.addItem("192.168.60.45");
		cb_ip.addItem("192.168.60.32");
		cb_ip.addItem("192.168.60.50");
		cb_ip.addItem("192.168.60.4");
		cb_ip.addItem("192.168.60.20");
		
		setSize(320, 530);
		setVisible(true);
		
	}
	
	
	@Override
	public void run() {
		connectServer();
	}
	
	
	//접속이란, 서버의 ip와 포트번호를 이용하여 소켓을 생성하는 것
	public void connectServer() {
		String ip = (String)cb_ip.getSelectedItem();
		int port = Integer.parseInt(t_port.getText());
		try {
			Socket socket = new Socket(ip, port);
			//접속 이후부터는 채팅은 쓰레드가 담당하므로, 소켓을 쓰레드에게 전달해주자.
			chatThread = new ClientChatThread(this, socket);
			chatThread.start();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Client();
	}
}
