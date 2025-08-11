package com.rex.pos.tos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rex.pos.models.MenuCategory;

public interface MenuCategoryRepository extends JpaRepository<MenuCategory, Long> {

	@EntityGraph(attributePaths = {"items"})
    List<MenuCategory> findByRestaurantIdOrderByNameAsc(Long restaurantId);
}
