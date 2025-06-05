package com.sinse.threadapp.ani;

import javax.swing.JLabel;

public class CountThread extends Thread{
	JLabel la;
	int velX;
	int count=0;
	
	public CountThread(JLabel la, int velX) {
		this.la = la;
		this.velX = velX;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(velX);
				la.setText(String.valueOf(count));
				count++;
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
}
