/**
 * 
 */
package com.rex.pos.dto;

/**
 * @author Rex
 *
 */
public class OrderItemDTO {

	private Long menuItemId;
	private int quantity;
	/**
	 * @return the menuItemId
	 */
	public Long getMenuItemId() {
		return menuItemId;
	}
	/**
	 * @param menuItemId the menuItemId to set
	 */
	public void setMenuItemId(Long menuItemId) {
		this.menuItemId = menuItemId;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
