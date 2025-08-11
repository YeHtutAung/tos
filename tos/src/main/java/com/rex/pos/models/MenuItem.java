/**
 * 
 */
package com.rex.pos.models;

import java.math.BigDecimal;

import com.rex.pos.common.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Rex
 *
 */
@Entity
@Data
@NoArgsConstructor
public class MenuItem extends Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;
	
	@Column(nullable = true)
	private String description;
	
	@Column(nullable = false)
	private BigDecimal price;
	
	@Column(nullable = false)
	private boolean isAvailable;

	@ManyToOne(optional = false)
    @JoinColumn(name = "category_id")
    private MenuCategory category;
	
}
