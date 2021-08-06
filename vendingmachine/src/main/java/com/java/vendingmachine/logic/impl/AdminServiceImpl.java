
package com.java.vendingmachine.logic.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.vendingmachine.database.entity.Inventory;
import com.java.vendingmachine.database.repository.InventoryRepository;
import com.java.vendingmachine.logic.api.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	InventoryRepository inventoryRepository;

	@Override
	public void addInventoryItem(Inventory inventoryItem) {

		inventoryRepository.save(inventoryItem);
	}

	@Override
	public List<Inventory> getAllInventoryList() {

		return inventoryRepository.findAll();
	}

	@Override
	public void updateItem(Inventory inventoryItem) {

		inventoryRepository.save(inventoryItem);

	}

	@Override
	public void removeItem(String name) {

		inventoryRepository.deleteById(name);
	}

	@Override
	public Inventory fetchIteByName(String name) {

		Optional<Inventory> item = inventoryRepository.findById(name);

		if (item.isPresent()) {

			return item.get();
			
		} else {
			
			return null;
		}
	}

}
