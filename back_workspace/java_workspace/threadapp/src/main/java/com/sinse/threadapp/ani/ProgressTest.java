package com.sinse.threadapp.ani;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

import com.sinse.threadapp.util.ProgressThread;

//스윙의 UI 컴포넌트 중, 진행율을 표현하는 컴포넌트인 JProgressBar를 사용해보자
public class ProgressTest extends JFrame{
	JProgressBar bar1;
	JProgressBar bar2;
	JProgressBar bar3;
	JButton bt;
	
	int n = 0;
	ProgressThread thread1;
	ProgressThread thread2;
	ProgressThread thread3;
	boolean flag = true;
	
	public ProgressTest() {
		bar1 = new JProgressBar();
		bar2 = new JProgressBar();
		bar3 = new JProgressBar();
		bt = new JButton("동작");
		
		thread1 = new ProgressThread(bar1, flag, 10, this);
		thread2 = new ProgressThread(bar2, flag, 100, this);
		thread3 = new ProgressThread(bar3, flag, 50, this);
		
		//스타일
		Dimension d = new Dimension(750, 45);
		bar1.setPreferredSize(d);
		bar2.setPreferredSize(d);
		bar3.setPreferredSize(d);
		
		setLayout(new FlowLayout());
		
		add(bar1);
		add(bar2);
		add(bar3);
		add(bt);
		
		bt.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				thread1.start();
				thread2.start();
				thread3.start();
			}
		});
		
		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void move(JProgressBar bar) {
		//bar1 증가
		n+=2;
		bar.setValue(n);
	}
	
	public static void main(String[] args) {
		new ProgressTest();
	}
}
