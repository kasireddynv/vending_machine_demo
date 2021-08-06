package com.java.vendingmachine.common;

public enum Product {

	SODA("SODA");

	private final String product;

	Product(final String product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return product;
	};
}
