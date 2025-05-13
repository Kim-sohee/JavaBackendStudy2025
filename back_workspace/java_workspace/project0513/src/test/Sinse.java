package test;

public class  Sinse{
	String name = "소희";
	int time = 8;
	
	/**
	집에 가고 싶은 시간을 입력하고 이 메서드 호출
	*/
	public void study(int t){
		this.time = t;
		System.out.println(this.time+"에 집에 가요");
	}
	
	/**
	메시지를 확인하고 싶으면 이 메서드 호출
	*/
	public void massge(){
		System.out.println("메롱 ~");
	}
}
