/*
메모장과 같은 텍스트파일이 아닌 이미지, 동영상 등의 바이너리 파일을 읽어보자
*/
package gui.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

class BinaryLoader {
	//실행중인 프로그램으로 데이터를 읽어들여야 하므로, 입력 스트림 객체가 필요
	FileInputStream fis;	//파일을 대상으로 한 입력 스트림
	FileOutputStream fos;
	
	String name = "C:/lecture_workspace/back_workspace/java_workspace/guiproject/res/cat.png";
	String target = "C:/lecture_workspace/back_workspace/java_workspace/guiproject/res/Copycat.png";
	
	public BinaryLoader(){
		try{
			fis = new FileInputStream(name);
			fos = new FileOutputStream(target);
			int data;
			
			while(true){
				data = fis.read();
				if(data == -1) break;
				System.out.println(data);
				
				//읽어들인 바이트 데이터를 내뱉는 빨대를 이용하여 출력해버리자
				fos.write(data);
			}
			
		}catch(FileNotFoundException e){
			System.out.println("파일이 없습니다.");
		}catch(IOException e){
			System.out.println("파일을 읽는 도중에 문제가 발생했습니다.");
		}finally{
			if(fis != null){
				try{
					fis.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
			if(fos != null){
				try{
					fos.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new BinaryLoader();
	}
}
