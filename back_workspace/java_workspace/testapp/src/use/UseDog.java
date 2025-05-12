/*다른 클래스를 사용하기 위한 클래스이므로, 실행부 정의하자*/
package use;	//개발자가 패키지를 선언하면 javac -d 옵션 사용시
					// 선언한 패키지에 해당하는 디렉토리 자동 생성

import animal.Dog;	//classpath 환경변수를 기준으로 그 밑의 animal 밑의 Dog.class를 임포트한다.

class  UseDog{
	public static void main(String[] args) {
		Dog dog = new Dog();
		dog.bark();
	}
}
