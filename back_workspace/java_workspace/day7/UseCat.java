/**/
class UseCat {
	public static void main(String[] args) 
	{
		// Cat클래스에 생성자가 눈에 보이지 않더라도 new 연산자 뒤에서 에러가 나지 않는 이유?
		// 컴파일러에 의한 디폴트 생성자의 존재 때문
		new Cat();
	}
}
