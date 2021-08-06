package com.java.vendingmachine.database.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Setter
@Getter
public class Inventory {

	@Id
	private String name;
	private BigDecimal price;
	private int quantity;
}
