/**
 * 
 */
package com.rex.pos.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * @author Rex
 *
 */
@Entity
public class Wyne {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int wyneNumber;

	private String status;

	// No-arg constructor
	public Wyne() {
	}

	// All-arg constructor (without ID)
	public Wyne(int wyneNumber, String status) {
		this.wyneNumber = wyneNumber;
		this.status = status;
	}

	// Full constructor (with ID) – optional
	public Wyne(Long id, int wyneNumber, String status) {
		this.id = id;
		this.wyneNumber = wyneNumber;
		this.status = status;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the wyneNumber
	 */
	public int getWyneNumber() {
		return wyneNumber;
	}

	/**
	 * @param wyneNumber the wyneNumber to set
	 */
	public void setWyneNumber(int wyneNumber) {
		this.wyneNumber = wyneNumber;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Wine [id=" + id + ", wyneNumber=" + wyneNumber + ", status=" + status + "]";
	}
}
