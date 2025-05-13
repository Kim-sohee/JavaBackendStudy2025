package use;

import use.Car;

public class UseCar  {
	public static void main(String[] args) {
		Car car = new Car();
		
		car.handle.rotate();
		car.wheel.roll();
		car.door.open();
		
		System.out.println(car.handle.color);
		System.out.println(car.wheel.brand);
	}
}
