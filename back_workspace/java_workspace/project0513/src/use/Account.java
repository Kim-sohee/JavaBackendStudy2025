package use;

/*객체 지향 언어의 장점
1) 캡슐화
2) 상속
3) 추상화
4) 
*/

public class Account{
	private String num = "123-23-43567-879";
	private String bank = "하나은행";
	private String owner = "Adams";
	private int balance = 500000;
	
	//클래스 작성 시, 데이터를 보호하고 보호된 데이터를 외부에서 사용하려면 공개된 메서드를 제공해주어야 한다.
	//이러한 클래스 기법을 은닉화,캡슐화 = encapsulation 이라 한다.
	
	//데이터에 대해 읽기, 쓰기가 가능하도록 메서드 정의
	//balance
	public int getBalance(){	//getter 메서드 => get + 멤버변수
		return balance;
	}
	public void setBalance(int balance){	//setter 메서드 => set + 멤버변수
		this.balance = balance;
	}
	
	//num
	public String getNum(){
		return num;
	}
	public void setNum(String num){
		this.num = num;
	}
	
	//bank
	public String getBank(){
		return bank;
	}
	public void setBank(String bank){
		this.bank = bank;
	}
	
	//owner
	public String getOwner(){
		return owner;
	}
	public void setOwner(String owner){
		this.owner = owner;
	}
	
	public static void main(String[] args){
		Account a = new Account();
		System.out.println(a.balance);
	}
}
