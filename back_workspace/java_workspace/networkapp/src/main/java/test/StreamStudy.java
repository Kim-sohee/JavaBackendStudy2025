package test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class StreamStudy {
	String path = "C:/public/data.txt";
	
	//바이트 기반의 스트림으로 읽기
	public void readByte() {
		//문서 파일을 읽고 그 내용을 출력
		FileInputStream fis =null;
		try {
			fis = new FileInputStream(path);
			while(true) {
				int result = 0;
				result = fis.read();
				if(result == -1) break;
				System.out.print((char)result);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
			
	}
	
	//문자 기반의 스트림으로 읽기
	public void readReader() {
		FileInputStream fis = null;		//바이트 기반
		InputStreamReader reader=null;	//문자 기반(바이트 기반 위에 생성)
		
		try {
			fis = new FileInputStream(path);
			reader = new InputStreamReader(fis);
			
//			while(true) {
//				int result = 0;
//				result = reader.read();
//				if(result == -1) break;
//				System.out.print((char)result);
//			}
			
			//주의) 문자기반 스트림은 데이터를 읽어들일 때 1문자씩 읽어들임
			//오해하지 않기) 한글이 깨지지 않는다고 하여 2byte씩 읽어들이는 거 아님. 2byte를 묶어서 문자로 해설할 수 있는 능력이 있는 것일 뿐..
			int data = -1;
			data= reader.read(); System.out.print((char) data);
			data= reader.read(); System.out.print((char) data);
			data= reader.read(); System.out.print((char) data);
			data= reader.read(); System.out.print((char) data);
			data= reader.read(); System.out.print((char) data);
			data= reader.read(); System.out.print((char) data);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//버퍼 기반의 스트림으로 읽기
	public void readBuffer() {
		FileInputStream fis = null;		//영문이 깨지지 않는 스트림
		InputStreamReader reader = null;	//한글까지 깨지지 않는 스트림
		BufferedReader buffr = null;		//한글까지 깨지지 않으면서 줄바꿈 문자를 만날때까지 읽지 않고 버퍼에 문자를 모음
		
		try {
			fis = new FileInputStream(path);
			reader = new InputStreamReader(fis);
			buffr = new BufferedReader(reader);

			while(true) {				
				String data = buffr.readLine();
				if(data == null) break;
				System.out.print(data);
			}
			
			
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
		StreamStudy ss = new StreamStudy();
//		ss.readByte();
//		ss.readReader();
		ss.readBuffer();
	}
}
