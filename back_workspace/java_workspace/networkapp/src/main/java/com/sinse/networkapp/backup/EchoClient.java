package com.sinse.networkapp.backup;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

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
		
		setSize(320, 530);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new EchoClient();
	}
}
