/*변수와 자료형*/
class  Test2{
	//자바의 실행부, 이 함수가 없다면 java.exe의 대상이 될 수 없다. -> 실행 불가능
	public static void main(String[] args){
		// 기본 자료형 = 문자, 숫자, 논리값
		
		// 문자 자료형
		char a = 'A';
		String str = "대한민국";
		
		//논리값
		Boolean b = true; //주의: 다른 언어에서는 1이 true, 0이 false를 대신할 수 있지만 자바는 철저히 true/false로 구분해서 표기해야 한다.
		
		//숫자 자료형
		//정수는 용량에 따라 byte < short < int< long
		int number = 30;
		
		//실수는 용량에 따라 float < double
		double y = 5.6;	//실수형의 default는 double이다.
		
		System.out.println(str);
	}
}
