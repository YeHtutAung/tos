package com.rex.pos.models;

import java.util.List;

import com.rex.pos.common.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Restaurant extends Auditable {

	@Id
	@GeneratedValue
	private Long restaurantId;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String address;
	
	@Column(nullable = true)
	private String contactPhone;
	
	@OneToMany(mappedBy = "restaurant")
	private List<MenuCategory> categories;
}
