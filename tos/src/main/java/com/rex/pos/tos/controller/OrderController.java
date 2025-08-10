/**
 * 
 */
package com.rex.pos.tos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rex.pos.dto.OrderRequestDTO;
import com.rex.pos.models.Order;
import com.rex.pos.tos.service.OrderService;

/**
 * @author Rex
 *
 */
@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping
	public ResponseEntity<Order> placeOrder(@RequestBody OrderRequestDTO dto) {
		return ResponseEntity.ok(orderService.placeOrder(dto));
	}
}
