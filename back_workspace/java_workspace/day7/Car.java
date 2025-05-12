class SportsCar{
	String color = "None Color";
	
	//이 클래스로부터 생성되는 인스턴스를 검정색 자동차로 생산
	public SportsCar(String color){
		this.color = color;
	}
	
	//생산된 이후에도 색상을 변경
	public void setColor(String color){
		this.color = color;
	}
}

class Car{
	public static void main(String[] args){
		SportsCar car = new SportsCar("Black");
		System.out.println(car.color);
		car.setColor("Red");
		System.out.println(car.color);
	}
}