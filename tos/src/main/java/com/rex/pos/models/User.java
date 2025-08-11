package com.rex.pos.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.rex.pos.common.Auditable;
import com.rex.pos.common.CryptoConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
	private Long Id;
	
	@Column(nullable = false) //Required
	private String name;
	
	@Convert(converter = CryptoConverter.class)
	@Column(nullable = false, unique = true)  // Required + Unique
	private String email;
	
	@Column(nullable = false)  // Required
	private String password;
	
	@Convert(converter = CryptoConverter.class)
	@Column(nullable = true)  // Optional (can be null)
	private String phone;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)  // Required
	private UserRole role; // Enum: CUSTOMER, WAITER, CHEF, ADMIN

	// Use a single shared PasswordEncoder bean instead of creating a new one every call (for performance)
	private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
	
	public enum UserRole {
		CUSTOMER, // Regular dining customers
		WAITER, // Staff who take/serve orders
		CHEF, // Kitchen staff who prepare orders
		ADMIN // Managers with full system access
	}
	
	public void setPassword(String rawPassword) {
	    this.password = PASSWORD_ENCODER.encode(rawPassword);
	}
}
