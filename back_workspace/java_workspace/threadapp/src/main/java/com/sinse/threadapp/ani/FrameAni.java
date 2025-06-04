package com.sinse.threadapp.ani;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sinse.threadapp.util.ImageUtil;

public class FrameAni extends JFrame{
	JPanel p_center;
	ImageUtil imageUtil = new ImageUtil();
	Image image= null;
	
	Thread thread;
	int num=1;
	
	public FrameAni() {
		p_center = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image, 100, 100, 300, 300, p_center);
			}
		};
		
		thread = new Thread() {
			public void run() {
				while(true) {					
					move();
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		add(p_center);
		
		thread.start();	//JVM에게 runnable 상태로의 진입을 부탁 -> jvm은 os에게 native 스레드 생성을 요청
		
		setSize(500, 500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void move() {
		image = imageUtil.getImage("images/hero/image"+num+".png", 300, 300);
		if(num>=18) {
			num=1;
		}else {
			num++;
		}
		p_center.repaint();
	}
	
	public static void main(String[] args) {
		new FrameAni();
	}
}
