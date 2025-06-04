package com.sinse.threadapp;

public class App 
{
    public static void main( String[] args ){
        //하나의 프로세스 내에서 각각 독립적으로 실행할 수 있는 단위인 쓰레드를 2개 생성하여
    	//각각 별도로 작동하게 해보자
    	ThreadA t1=new ThreadA();
    	ThreadB t2=new ThreadB();
    	
    	//쓰레드를 생성한다고 하여 OS가 관여하는게 아니라, start로 밀어 넣어야 한다.
    	//JVM에게 맡겨야 한다.
    	t1.start();
    	t2.start();
    }
}
