package com.sinse.threadapp.ani;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CountNumber extends JFrame{
	JButton bt;
	JPanel p_center;
	JLabel la1;
	JLabel la2;
	
	CountThread t1;
	CountThread t2;
	
	public CountNumber() {
		bt = new JButton("시작하기");
		p_center = new JPanel();
		la1 = new JLabel("0");
		la2 = new JLabel("0");
		
		//스타일
		Dimension d = new Dimension(300, 400);
		la1.setPreferredSize(d);
		la2.setPreferredSize(d);
		
		Font f = new Font(null, Font.BOLD, 100);
		la1.setFont(f);
		la2.setFont(f);
		
		//조립
		add(bt, BorderLayout.NORTH);
		p_center.add(la1);
		p_center.add(la2);
		add(p_center);
		
		//버튼 클릭
		bt.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				t1 = new CountThread(la1, 100);
				t2 = new CountThread(la2, 300);
				
				t1.start();
				t2.start();
			}
		});
		
		setSize(650, 400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new CountNumber();
	}
}
