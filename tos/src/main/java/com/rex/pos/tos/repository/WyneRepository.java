/**
 * 
 */
package com.rex.pos.tos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rex.pos.models.Wyne;

/**
 * @author Rex
 *
 */
public interface WyneRepository extends JpaRepository<Wyne, Long> {

	Page<Wyne> findByRestaurantId(Long restaurantId, Pageable pageable);
}
