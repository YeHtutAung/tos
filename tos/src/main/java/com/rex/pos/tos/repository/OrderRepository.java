/**
 * 
 */
package com.rex.pos.tos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rex.pos.models.Order;

/**
 * @author Rex
 *
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

}
