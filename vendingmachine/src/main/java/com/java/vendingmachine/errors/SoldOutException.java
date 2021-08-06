package com.java.vendingmachine.errors;

public class SoldOutException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4302683059939859041L;

	public SoldOutException(String msg) {

		super(msg);
	}

}
