class ObjectType {
	public static void main(String[] args){
		//자바에서는 자료형이 총 4개가 지원된다.
		//기본자료형(문자, 숫자, 논리값), 객체 자료형
		int x=5;
		
		//자바에서는 개발자가 정의한 클래스를 새로운 자료형으로 인정해준다.
		Dog d = new Dog();
		
		d.bark();
	}
}