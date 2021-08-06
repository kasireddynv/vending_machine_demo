
package com.java.vendingmachine.logic.api;

import java.util.List;

import org.springframework.stereotype.Service;

import com.java.vendingmachine.database.entity.Inventory;

@Service
public interface AdminService {

	public void addInventoryItem(Inventory inventoryItem);

	public List<Inventory> getAllInventoryList();

	public void updateItem(Inventory inventoryItem);

	public void removeItem(String name);

	public Inventory fetchIteByName(String name);

}
