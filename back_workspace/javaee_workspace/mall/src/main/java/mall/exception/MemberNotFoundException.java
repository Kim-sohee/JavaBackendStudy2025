package mall.exception;

public class MemberNotFoundException extends RuntimeException{
	
	public MemberNotFoundException(String msg) {
		super(msg);		//생성자는 물려받지 못하므로, 부모의 생성자를 호출한다.
	}
	
	public MemberNotFoundException(String msg, Throwable e) {
		super(msg, e);
	}
	
	public MemberNotFoundException(Throwable e) {
		super(e);
	}
}
