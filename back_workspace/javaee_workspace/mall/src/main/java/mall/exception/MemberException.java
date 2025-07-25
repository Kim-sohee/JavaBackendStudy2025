package mall.exception;

public class MemberException extends RuntimeException{
	
	public MemberException(String msg) {
		super(msg);		//생성자는 물려받지 못하므로, 부모의 생성자를 호출한다.
	}
	
	public MemberException(String msg, Throwable e) {
		super(msg, e);
	}
	
	public MemberException(Throwable e) {
		super(e);
	}
}
