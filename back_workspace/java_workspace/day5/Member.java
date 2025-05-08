class Member { //A
	int age=23;
	static int money=23;
	
	public void talk(){
	}
}

public class UseMember{
	static String k=8;
	
	public static void main(String[] args){
		//방법1
		UseMember.k = 9;
		
		//방법2 : main과 UseMember가 같은 클래스 안에 있으면서 static인 경우
		k = 10;
	}
}
