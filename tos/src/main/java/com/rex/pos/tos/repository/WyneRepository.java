/**
 * 
 */
package com.rex.pos.tos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rex.pos.models.Wyne;

/**
 * @author Rex
 *
 */
public interface WyneRepository extends JpaRepository<Wyne, Long> {

	Optional<Wyne> findByIdAndRestaurantId(Long id, Long restaurantId);
}
