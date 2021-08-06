package com.java.vendingmachine.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.vendingmachine.database.entity.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, String> {

}
