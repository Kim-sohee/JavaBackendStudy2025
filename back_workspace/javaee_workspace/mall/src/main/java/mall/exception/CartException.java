package mall.exception;

public class CartException extends RuntimeException{
	
	public CartException(String msg) {
		super(msg);		//생성자는 물려받지 못하므로, 부모의 생성자를 호출한다.
	}
	
	public CartException(String msg, Throwable e) {
		super(msg, e);
	}
	
	public CartException(Throwable e) {
		super(e);
	}
}
