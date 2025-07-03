package com.sinse.boradapp.exception;

public class NewsException extends RuntimeException{
	
	public NewsException(String msg) {
		super(msg);
	}
	
	public NewsException(Throwable e) {
		super(e);
	}
	
	public NewsException(String msg, Throwable e) {
		super(msg, e);
	}
}
