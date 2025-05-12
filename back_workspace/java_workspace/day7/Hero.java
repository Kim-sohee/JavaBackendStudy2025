public class Hero{
	int hp = 10;
	boolean fly = false;
	String name = "수퍼마리오";
	Bullet bullet;
	
	public void setHp(int hp){
		this.hp = hp;
	}
	public void setFly(boolean fly){
		this.fly = fly;
	}
	public void setName(String name){
		this.name = name;
	}
	public void fire(Bullet bullet){
		this.bullet = bullet;
	}
	
	public static void main(String[] args){
		Hero hero = new Hero();
		hero.setHp(20);
		hero.setFly(true);
		hero.setName("루이지");
		hero.fire(new Bullet());
	}
}