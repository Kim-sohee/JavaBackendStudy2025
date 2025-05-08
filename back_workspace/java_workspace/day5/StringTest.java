class StringTest{
	public static void main(String[] args){
		//모든 프로그래밍 언어에서 개발자가 사용할 수 있는 기본 자려형은
		//문자, 숫자, 논리값이지만 이중 문자는 문자 1개를 말하므로
		//문자열을 표현하려면 개발자가 배열을 사용해야 한다.
		//불편함-> 내부적으로는 배열로 처리되지만 개발자를 위한 문자열에 대해서는 객체 자료형으로 지원해준다.
		//new를 명시하지 않아도, 내부적으로 문자열 객체를 생성시키는 생성법을 암시적, 묵시적 생성법이라 한다.
		String s1 = "korea";
		String s2 = "korea";
		System.out.println(s1==s2);	//주소비교
		
		//new 연산자를 이용하여 객체의 생성법을 그대로 따르는 문자열 생성법을 명시적 생성법이라 한다.
		//아래는 주소값 비교이다.
		String x1 = new String("apple");
		String x2 = new String("apple");
		System.out.println(x1==x2);
	}
}