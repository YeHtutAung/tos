/**
 * 
 */
package com.rex.pos.tos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rex.pos.models.MenuItem;

/**
 * @author Rex
 *
 */
public interface MenuItemRepository extends JpaRepository<MenuItem, Long>{

}
