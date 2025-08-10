/**
 * 
 */
package com.rex.pos.models;

import com.rex.pos.common.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

	@Column(nullable = false) //Required
	private int wyneNumber;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false) //Required
	private WyneStatus status = WyneStatus.AVALIABLE;

	public enum WyneStatus {
		AVALIABLE,
		NOT_AVALIABLE,
		OCCUPIED, 
		CLEARING 
	}
}
