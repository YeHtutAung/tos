package com.rex.pos.tos.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.rex.pos.dto.MenuCategoryDto;
import com.rex.pos.dto.MenuItemDto;
import com.rex.pos.models.MenuCategory;
import com.rex.pos.models.MenuItem;
import com.rex.pos.tos.repository.MenuCategoryRepository;

@Service
public class MenuCategoryService {

	private final MenuCategoryRepository categoryRepo;

	public MenuCategoryService(MenuCategoryRepository categoryRepo) {
		this.categoryRepo = categoryRepo;
	}

	@Cacheable(cacheNames = "menu", key = "#restaurantId")
	public List<MenuCategoryDto> getMenuByRestaurant(Long restaurantId) {
		return categoryRepo.findByRestaurantIdOrderByNameAsc(restaurantId).stream().map(this::toDto).toList();
	}

	private MenuCategoryDto toDto(MenuCategory c) {
		return new MenuCategoryDto(c.getId(), c.getName(), c.getDescription(),
				c.getItems() == null ? List.of() : c.getItems().stream().map(this::toDto).toList());
	}

	private MenuItemDto toDto(MenuItem i) {
		return new MenuItemDto(i.getId(), i.getName(), i.getDescription(), i.getPrice(), i.isAvailable(),
				i.getCategory() != null ? i.getCategory().getId() : null);
	}
}
