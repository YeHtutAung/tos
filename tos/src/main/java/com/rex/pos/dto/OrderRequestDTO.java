/**
 * 
 */
package com.rex.pos.dto;

import java.util.List;

/**
 * @author yehtu
 *
 */
public class OrderRequestDTO {

	private Long wyneId;
    private List<OrderItemDTO> items;
	/**
	 * @return the wyneId
	 */
	public Long getWyneId() {
		return wyneId;
	}
	/**
	 * @param wyneId the wyneId to set
	 */
	public void setWyneId(Long wyneId) {
		this.wyneId = wyneId;
	}
	/**
	 * @return the items
	 */
	public List<OrderItemDTO> getItems() {
		return items;
	}
	/**
	 * @param items the items to set
	 */
	public void setItems(List<OrderItemDTO> items) {
		this.items = items;
	}
}
