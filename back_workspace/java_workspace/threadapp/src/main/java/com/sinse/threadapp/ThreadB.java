package com.sinse.threadapp;

public class ThreadB extends Thread{
	@Override
	public void run() {
		for(int j=1; j<=50; j++) {
        	System.out.println("B");
        }
	}
}
