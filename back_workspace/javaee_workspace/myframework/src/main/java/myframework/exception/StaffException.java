package myframework.exception;

public class StaffException extends RuntimeException{
	public StaffException(String msg) {
		super(msg);
	}
	
	public StaffException(Throwable e) {
		super(e);
	}
	
	public StaffException(String msg, Throwable e) {
		super(msg, e);
	}
}
