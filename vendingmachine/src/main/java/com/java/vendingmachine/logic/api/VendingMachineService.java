package com.java.vendingmachine.logic.api;

import org.springframework.stereotype.Service;

@Service
public interface VendingMachineService {

	void insertQuarter(int quarter);
	
	void removeQuarter();
	
	void pushSodaButton();
	
	void dispenseSoda();
}
