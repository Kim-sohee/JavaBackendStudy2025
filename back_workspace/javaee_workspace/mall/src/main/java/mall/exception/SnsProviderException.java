package mall.exception;

public class SnsProviderException extends RuntimeException{
	
	public SnsProviderException(String msg) {
		super(msg);		//생성자는 물려받지 못하므로, 부모의 생성자를 호출한다.
	}
	
	public SnsProviderException(String msg, Throwable e) {
		super(msg, e);
	}
	
	public SnsProviderException(Throwable e) {
		super(e);
	}
}
