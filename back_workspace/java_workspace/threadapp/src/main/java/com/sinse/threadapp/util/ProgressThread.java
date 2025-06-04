package com.sinse.threadapp.util;

import javax.swing.JProgressBar;

import com.sinse.threadapp.ani.ProgressTest;

public class ProgressThread extends Thread{
	ProgressTest pg;
	JProgressBar bar;
	boolean flag;
	int time=0;
	
	public ProgressThread(JProgressBar bar, boolean flag, int time, ProgressTest pg) {
		this.bar = bar;
		this.flag = flag;
		this.time = time;
		this.pg = pg;
	}
	
	@Override
	public void run() {
		while(flag) {
			try {
				Thread.sleep(time);
				pg.move(bar);				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}
}
