package use;

class AccountHack {
	public static void main(String[] args) {
		Account acc = new Account();
		acc.setBalance(500);
		System.out.println(acc.getBalance());
	}
}
