/*최대한, 현실을 반영하여 자동차를 정의해보자
조건1) 핸들이 있어야 함
2)	바퀴도 있어야 함
3) 문짝도 있어야 함
*/
package use;

class Car {
	int price;
	String name;
	
	//객체가 다른 객체를 멤버변수로 보유한 관계를 has a 관계라 한다.
	Handle handle;	//has a 관계 : Car has a Handle
	Wheel wheel;
	Door door;
	
	//생성자로 초기화
	//has a 관계에 있는 객체의 인스턴스를 생성할 때 아주 유용함
	public Car(){
		price = 5000;
		name = "K9";
		handle = new Handle();	//has a 관계 : Car has a Handle
		wheel = new Wheel();
		door = new Door();
	}
}
