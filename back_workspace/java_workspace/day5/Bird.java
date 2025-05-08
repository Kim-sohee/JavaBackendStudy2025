class Bird{
	String name = "독수리";
	
	public String getName(){
		return name;
	}
	
	public static void main(String[] args){
		int x = 5;
		Bird b = new Bird();
		b.getName();
		
		Bird b2 = new Bird();
		b2.getName();
		
		System.out.println(b);
	}
}