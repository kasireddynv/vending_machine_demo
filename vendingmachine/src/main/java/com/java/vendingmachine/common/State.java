package com.java.vendingmachine.common;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class State {

	private State() {

	}

	private String state = VendingMachineStates.NO_QUARTER;

	private static State instance = null;

	public static State getInstance() {

		if (null == instance) {

			instance = new State();
		}

		return instance;
	}
}
