class StringTest2{
	public static void main(String[] args){
		/*String은 불변의 특징이 있다. 즉 변경될 수 없다.(Immutable)*/
		String x = "korea";
		x="korea fighting";
		
		//회사에서 String을 아래와 같이 하면 끌려감
		for(int i=1; i<=100; i++){
			x = x+i;
			System.out.println(x);
		}	
	}
}