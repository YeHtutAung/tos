package com.rex.pos.models;

import com.rex.pos.common.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User extends Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@Column(nullable = false) //Required
	private String name;
	
	@Column(nullable = false, unique = true)  // Required + Unique
	private String email;
	
	@Column(nullable = false)  // Required
	private String password;
	
	@Column(nullable = true)  // Optional (can be null)
	private String phone;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)  // Required
	private UserRole role; // Enum: CUSTOMER, WAITER, CHEF, ADMIN

	
	public enum UserRole {
		CUSTOMER, // Regular dining customers
		WAITER, // Staff who take/serve orders
		CHEF, // Kitchen staff who prepare orders
		ADMIN // Managers with full system access
	}
}
