package com.java.vendingmachine.errors;

public class InternalServerError extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2002565500691363310L;

	public InternalServerError(String msg) {
		super(msg);
	}

}
