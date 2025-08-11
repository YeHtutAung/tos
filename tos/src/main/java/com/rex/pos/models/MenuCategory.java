package com.rex.pos.models;

import java.util.List;

import com.rex.pos.common.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class MenuCategory extends Auditable {

	@Id
	@GeneratedValue
	private Long Id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = true)
	private String description; // Optional

	@ManyToOne
	private Restaurant restaurant;

	@OneToMany(mappedBy = "category")
	private List<MenuItem> items;
}
