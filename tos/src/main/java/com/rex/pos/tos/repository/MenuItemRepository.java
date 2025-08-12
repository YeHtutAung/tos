/**
 * 
 */
package com.rex.pos.tos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rex.pos.models.MenuItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Rex
 *
 */
public interface MenuItemRepository extends JpaRepository<MenuItem, Long>{

	Page<MenuItem> findByCategoryId(Long Id, Pageable pageable);
}
