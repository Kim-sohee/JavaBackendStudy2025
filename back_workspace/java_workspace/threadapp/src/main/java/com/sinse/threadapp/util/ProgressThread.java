package com.sinse.threadapp.util;

import javax.swing.JProgressBar;

public class ProgressThread extends Thread{

	JProgressBar bar;
	int n;
	int velX;
	
	public ProgressThread(JProgressBar bar, int velX) {
		this.bar = bar;
		this.velX = velX;
	}
	
	@Override
	public void run() {
		while(true) {			
			try {
				Thread.sleep(velX);
				bar.setValue(n);
				n++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
