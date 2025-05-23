/*대량의 데이터를 읽어보기*/
package com.sinse.ioproject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class StreamTuning {
	
	FileInputStream fis;
	
	//효율성을 떠나, 한글이 깨지지 않고 나오게 하기 위해 스트림을 기존 스트림에 문자기반 스트림을 덧씌우자
	InputStreamReader reader;	//한 문자씩 읽음
	
	//한줄을 만날때까지는 입력을 보류하다가, 한줄의 끝인 줄바꿈 특수문자를 만나면 그제서야 한번 읽어들이는 
	//버퍼를 이용한 문자열 처리 전용 입력 스트립으로 업그레이드
	BufferedReader buffr;
	
	String name = "C:/lecture_workspace/back_workspace/java_workspace/IOProject/res/memo2.txt";
	
	public StreamTuning() {
		try {
			fis = new FileInputStream(name);
			//빨대 업그레이드
			reader = new InputStreamReader(fis);
			
			//버퍼 처리된 빨대로 업그레이드
			buffr = new BufferedReader(reader);
			
			int count = 0;	//읽은 횟수 체크용 변수
			String data = null;
			
			while(true) {
				data = buffr.readLine();
				if(data == null) break;
				count++;
				System.out.println(data);
			}
			
			System.out.println("\n총 읽은 횟수: "+count);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(buffr != null) {
				try {
					buffr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new StreamTuning();
	}
	
}
