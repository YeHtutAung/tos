/**
 * 
 */
package com.rex.pos.models;

import java.time.LocalDateTime;
import java.util.List;

import com.rex.pos.common.Auditable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * @author yehtu
 *
 */
@Entity
@Table(name = "orders") // 'Order' is a reserved keyword in SQL
public class Order extends Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@Column(nullable = true)
	private Wyne wyne;

	@ManyToOne
	@Column(nullable = true)
	private User user;

	@Column(nullable = false)
	private LocalDateTime orderTime;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private OrderStatus status = OrderStatus.PENDING; // "PENDING", "COMPLETED"

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> items;

	@Column(nullable = false)
	private double totalAmount;
	
	@Column(nullable = true)
    private String deliveryAddress; 

	public enum OrderStatus {
		PENDING, DELIVERED, PREPARING, COMPLETED
	}
}
