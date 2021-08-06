package com.java.vendingmachine.errors;

public class NoSufficientQuarterException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2002565500691363310L;

	public NoSufficientQuarterException(String msg) {
		super(msg);
	}

}
