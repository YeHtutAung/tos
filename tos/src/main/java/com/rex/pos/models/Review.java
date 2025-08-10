package com.rex.pos.models;

import com.rex.pos.common.Auditable;

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
	private Long reviewId;

	@ManyToOne(optional = true)
	private User user;

	@ManyToOne(optional = false)
	private Order order;

	@ManyToOne(optional = false)
	private int rating;

	@ManyToOne(optional = true)
	private String comment;
}
