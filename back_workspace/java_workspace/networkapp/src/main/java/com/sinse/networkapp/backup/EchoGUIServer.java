package com.sinse.networkapp.backup;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EchoGUIServer extends JFrame{
	JPanel p_north;
	JTextField t_port;
	JButton bt;
	JTextArea area;
	
	public EchoGUIServer() {
		p_north = new JPanel();
		t_port = new JTextField();
		bt = new JButton("서버 가동");
		area = new JTextArea();
		
		//스타일
		p_north.setPreferredSize(new Dimension(300, 40));
		p_north.setBackground(Color.LIGHT_GRAY);
		t_port.setPreferredSize(new Dimension(150, 30));
		
		area.setBackground(Color.yellow);
		
		// 조립
		p_north.add(t_port);
		p_north.add(bt);
		add(p_north, BorderLayout.NORTH);
		add(area);
		
		setSize(300, 400);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new EchoGUIServer();
	}
}
