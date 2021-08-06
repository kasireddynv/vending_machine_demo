package com.java.vendingmachine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.java.vendingmachine.database.entity.Inventory;
import com.java.vendingmachine.logic.api.AdminService;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	AdminService adminService;

	@RequestMapping(value = "/addItem", method = RequestMethod.POST)
	public ResponseEntity<Object> createItem(@Validated @RequestBody Inventory inventory) {

		try {

			adminService.addInventoryItem(inventory);

			return new ResponseEntity<Object>("InventoryItem Added Successfully", HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<Object>("Something went wrong..Please try again",
					HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@RequestMapping(value = "/item/{name}", method = RequestMethod.GET)
	public ResponseEntity<Object> getItemById(@PathVariable String name) {

		try {

			return new ResponseEntity<Object>(adminService.fetchIteByName(name), HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<Object>("Something went wrong..Please try again",
					HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@RequestMapping(value = "/item/delete/{name}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteItem(@PathVariable String name) {
		try {

			adminService.removeItem(name);

			return new ResponseEntity<Object>("InventoryItem removed Successfully", HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<Object>("Something went wrong..Please try again",
					HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@RequestMapping(value = "/updateItem", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateItem(@Validated @RequestBody Inventory inventoryItem) {
		try {

			adminService.addInventoryItem(inventoryItem);

			return new ResponseEntity<Object>("InventoryItem Updated Successfully", HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<Object>("Something went wrong..Please try again",
					HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@RequestMapping(value = "/allInventoryItems", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllItems() {
		try {

			return new ResponseEntity<Object>(adminService.getAllInventoryList(), HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<Object>("Something went wrong..Please try again",
					HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
}
