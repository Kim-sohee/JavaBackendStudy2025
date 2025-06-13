package com.sinse.shopadmin.product.view;

public class NumberConvertFailException extends RuntimeException {
	
	public NumberConvertFailException(String msg) {
		super(msg);
	}
	public NumberConvertFailException(Throwable a) {
		super(a);
	}
	public NumberConvertFailException(String msg, Throwable a) {
		super(msg, a);
	}
}
