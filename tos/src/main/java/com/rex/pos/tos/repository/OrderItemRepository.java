/**
 * 
 */
package com.rex.pos.tos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rex.pos.models.OrderItem;

/**
 * @author Rex
 *
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
