class UseDuck{
	public static void main(String[] args){
		//Duck 클래스 자체는 오리를 생성할 수 있는 틀에 불과하지 오리 자체가 아님
		// 사용하려면 반드시 인스턴스를 생성한 후 접근해야 한다.
		Duck d = new Duck();
		System.out.println(d.age);
	}
}