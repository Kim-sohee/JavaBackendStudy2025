package com.sinse.networkapp.echo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EchoClient extends JFrame{
	JPanel p_north;
	JComboBox cb_ip;
	JTextField t_port;
	JButton bt_con;
	
	JPanel p_center;
	JTextArea area;
	JTextField t_input;
	Socket socket;		//대화용 소켓, 이 객체를 메모리에 올릴 때 접속이 발생함
								//또한 접속이 성공되면, 그 시점부터 연결이 이루어진 것이므로, 스트림을 통해 데이터를 주고 받을 수 있다.
	BufferedWriter buffw;
	BufferedReader buffr;

	
	public EchoClient() {
		//생성
		p_north = new JPanel();
		cb_ip = new JComboBox();
		t_port = new JTextField();
		bt_con = new JButton("연결");
		
		p_center = new JPanel();
		area = new JTextArea();
		t_input = new JTextField();
		
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
		
		p_center.add(area);
		p_center.add(t_input);
		add(p_center);
		
		//이벤트
		cb_ip.addItem("192.168.60.45");
		cb_ip.addItem("192.168.60.32");
		cb_ip.addItem("192.168.60.50");
		cb_ip.addItem("192.168.60.4");
		
		//람다는 반드시 함수형 인터페이스(메서드 1개)에만 사용 가능
		bt_con.addActionListener(e->{
			connect();
		});
		
		t_input.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					//서버로 내보내기(출력)
					String msg = t_input.getText();
					send(msg);
				}
			}
		});
		
		setSize(320, 530);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void connect() {
		//소켓 서버에 접속해보기
		try {
			socket = new Socket((String)cb_ip.getSelectedItem(), Integer.parseInt(t_port.getText()));
			
			//소켓으로부터 스트림을 얻어오자
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));		//바이트 기반, 출력스트림
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//실행중인 프로그램에서, 데이터를 내보내야 하므로, 필요한 스트림은 바로 출력 스트림이다.
	public void send(String msg) {
		try {
			//서버로 한 줄 보내기
			buffw.write(msg+"\n");
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public static void main(String[] args) {
		new EchoClient();
	}
}
