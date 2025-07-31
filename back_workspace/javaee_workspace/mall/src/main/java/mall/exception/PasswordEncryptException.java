package mall.exception;

public class PasswordEncryptException extends RuntimeException{
	
	public PasswordEncryptException(String msg) {
		super(msg);		//생성자는 물려받지 못하므로, 부모의 생성자를 호출한다.
	}
	
	public PasswordEncryptException(String msg, Throwable e) {
		super(msg, e);
	}
	
	public PasswordEncryptException(Throwable e) {
		super(e);
	}
}
