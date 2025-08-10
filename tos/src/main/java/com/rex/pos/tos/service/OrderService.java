/**
 * 
 */
package com.rex.pos.tos.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rex.pos.dto.OrderItemDTO;
import com.rex.pos.dto.OrderRequestDTO;
import com.rex.pos.models.MenuItem;
import com.rex.pos.models.Order;
import com.rex.pos.models.OrderItem;
import com.rex.pos.models.Wyne;
import com.rex.pos.tos.repository.MenuItemRepository;
import com.rex.pos.tos.repository.OrderRepository;
import com.rex.pos.tos.repository.WyneRepository;

import jakarta.persistence.EntityNotFoundException;

/**
 * @author Rex
 *
 */
@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private WyneRepository wyneRepo;
	@Autowired
	private MenuItemRepository menuItemRepo;

	public Order placeOrder(OrderRequestDTO dto) {

		Wyne wyne = wyneRepo.findById(dto.getWyneId())
				.orElseThrow(() -> new EntityNotFoundException("Table Not found"));
		wyne.setStatus("OCCUPIED");

		Order order = new Order();
		order.setWyne(wyne);
		order.setOrderTime(LocalDateTime.now());
		order.setStatus("PENDING");

		List<OrderItem> items = new ArrayList<>();
		for (OrderItemDTO itemDTO : dto.getItems()) {
			MenuItem menuItem = menuItemRepo.findById(itemDTO.getMenuItemId())
					.orElseThrow(() -> new EntityNotFoundException("Menu item not found"));

			OrderItem orderItem = new OrderItem();
			orderItem.setMenuItem(menuItem);
			orderItem.setQuantity(itemDTO.getQuantity());
			orderItem.setOrder(order);
			items.add(orderItem);
		}

		order.setItems(items);

		return orderRepo.save(order);
	}
}
