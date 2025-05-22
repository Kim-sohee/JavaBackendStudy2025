/*실행중인 자바 프로그램에서 디스크의 파일을 접근하여 데이터를 읽고, 
프로그램으로 불러들여 출력해보자*/
package gui.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

class FileLoader {
	public static void main(String[] args) {
		//실행중인 프로그램이 파일 등의 자원의 데이터를 읽어들이기 위해서는 스트림 객체가 필요
		/* Stream이란? 현실에서 물줄기, 물의 흐름을 의미한다. 그러나 전산에서는 그 대상이 데이터이다.
		흐름의 방향은 2가지 유형이 있음
		IO(입출력)
		1) 실행중인 프로그램으로 들어오는 흐름: Input(입력)
		2) 실행중인 프로그램에서 데이터가 나가면 Output(출력)
		*/
		
		//파일을 대상으로 한 입력 객체(파일을 읽어들일 수 있는 객체)
		String name = "C:/lecture_workspace/back_workspace/java_workspace/guiproject/res/memo.txt";
		
		//디스크에 있는 파일에 스트림이 생성
		//문법상은 문제 없지만, 프로그램 코드 상의 문제 외의 문제때문에  프로그램이 정상 수행될 수 없는 사오항이 될 수 있음
		//이 상황을 방지하기 위해 컴파일러 차원에서 컴파일 거부중
		FileInputStream fis=null;	//지역변수는 반드시 초기화 명시
		try{
			fis = new FileInputStream(name);
			int data; 
			
			while(true){
				data = fis.read();	//1byte 읽어들임
				if(data == -1) break;
				System.out.print((char)data);
			}
			
		}catch(FileNotFoundException e){	//catch문의 소괄호안에 에러의 원인이 되는 객체의 인스턴스를 생성하여 전달해준다.
			//에러의 원인 객체이므로 개발자가 추출해서 분석하거나 자세한 에러 파악이 가능하도록한다.
			//만약 파일이 없다면, 파일을 복구하지는 못하므로 원인을 알려주거나 로그를 남기는 등의 처리
			System.out.println("파일을 찾을 수 없습니다.");
		}catch(IOException e){
			System.out.println("입출력 도중 에러가 발생했습니다.");
		}finally{
			//실행부가 try문을 수행하던, catch문을 수행하던 반드시 거쳐나가는 영역
			
			//DB와 스트림은 반드시 닫아야 한다.
			if(fis != null){
				try{
					fis.close();		//스트림 닫기
				}catch(IOException e){
					//일반 유저가 아닌 개발자를 위한 로그 출력
					e.printStackTrace();
				}
			}
		}
	}
}
