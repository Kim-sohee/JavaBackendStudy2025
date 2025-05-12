class Computer {
	int speed = 300;
}

class UseComputer{
	public void setting(Computer c, int s){
		c.speed=s;
		s=50;
	}
	
	public static void main(String[] args){
		int k=500;
		
		UseComputer uc = new UseComputer();
		Computer com = new Computer();
		
		com.speed = 100;
		uc.setting(com, k);
		
		System.out.println(com.speed);
		System.out.println(k);
	}
}
