/**
 * 
 */
package com.rex.pos.models;

import com.rex.pos.common.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

/**
 * @author Rex
 *
 */
@Entity
@Data
public class Wyne extends Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "wyne_number", nullable = false) // Required
	private int wyneNumber;

	@Column(name = "qr_code", length = 255)
	private String qrCode;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "restaurant_id", nullable = false, foreignKey = @ForeignKey(name = "fk_table_restaurant"))
	private Restaurant restaurant;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false) // Required
	private WyneStatus status = WyneStatus.AVALIABLE;

	public enum WyneStatus {
		AVALIABLE, RESERVED, OCCUPIED, CLEARING
	}
}
