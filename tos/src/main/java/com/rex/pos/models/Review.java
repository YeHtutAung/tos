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
public class Review extends Auditable {

	@Id
	@GeneratedValue
	private Long Id;

	@ManyToOne(optional = true)
	private User user;

	@ManyToOne(optional = false)
	private Order order;

	@Column(nullable = true)
	private int rating;

	@Column(nullable = true)
	private String comment;
}
