package com.rex.pos.models;

import com.rex.pos.common.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Inventory extends Auditable {

	@Id
	@GeneratedValue
	private Long inventoryId;

	@Column(nullable = false)
	private String itemName;

	@Column(nullable = false)
	private double quantity;

	@Column(nullable = false)
	private String unit; // kg, liters, etc.

	@ManyToOne(optional = false)
	private Restaurant restaurant;
}
