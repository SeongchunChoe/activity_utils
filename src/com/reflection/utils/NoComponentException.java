package com.reflection.utils;

public class NoComponentException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mMsg;
	public NoComponentException(String msg){
		mMsg = msg;
	}
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return mMsg;
	}
	
}
