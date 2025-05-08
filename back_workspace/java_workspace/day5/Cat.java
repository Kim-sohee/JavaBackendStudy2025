/*
	JVM도 프로그램이므로, OS로부터 메모리를 할당받으면,
	해당 메모리를 3개의 영역으로 나누어서 관리한다.
	1) method: 클래스 바이트 코드가 로드 되는 영역,
			로드 시점은 실행할 때(java.exe) 이다.
	2) stack: 모든 지역변수
			-메서드 내에 선언된 지역변수
			-메서드의 매개변수
	3) heap: 객체 인스턴스가 올라오는 영역
*/
class Cat{
	String name = "tomcat";
	int age = 5;
	
	public int getAge(){
		return age;
	}
	
	public static void main(String[] args){
		//java.exe 호출 시 main()메서드는 자동 호출되는데,
		//이때 개발자는 main메서드의 매개변수인 String 배열의 값을 넘길 수 있다.
		// cmd -> java 문자열1 문자열2
		System.out.println("당신이 넘긴 배열의 수는 "+args.length);
		for(int i=0; i<args.length; i++){
			System.out.println(args[i]);
		}
		
		int x = 7;
		Cat c = new Cat();
		System.out.println(c.getAge());
	}
}