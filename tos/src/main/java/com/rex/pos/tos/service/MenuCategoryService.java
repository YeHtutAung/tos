package com.rex.pos.tos.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.rex.pos.dto.MenuCategoryDTO.Create;
import com.rex.pos.dto.MenuCategoryDTO.Update;
import com.rex.pos.dto.MenuCategoryDTO.View;
import com.rex.pos.dto.common.PageResponse;
import com.rex.pos.models.MenuCategory;
import com.rex.pos.models.Restaurant;
import com.rex.pos.tos.repository.MenuCategoryRepository;
import com.rex.pos.tos.repository.RestaurantRepository;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuCategoryService {

	private final MenuCategoryRepository repo;
	private final RestaurantRepository restaurantRepo;

	@Transactional
	public View create(Create req) {
		Restaurant rest = restaurantRepo.findById(req.restaurantId())
				.orElseThrow(() -> new IllegalArgumentException("Restaurant not found: " + req.restaurantId()));
		MenuCategory c = new MenuCategory();
		c.setRestaurant(rest);
		c.setName(req.name());
		c.setDescription(req.description());
		c = repo.save(c);
		return new View(c.getId(), c.getName(), c.getDescription(), rest.getId());
	}

	@Transactional(readOnly = true)
	public PageResponse<View> listByRestaurant(Long restaurantId, int page, int size) {
		Page<MenuCategory> p = repo.findByRestaurantId(restaurantId, PageRequest.of(page, size));
		return new PageResponse<>(
				p.map(c -> new View(c.getId(), c.getName(), c.getDescription(), c.getRestaurant().getId()))
						.getContent(),
				p.getTotalElements(), p.getTotalPages(), page, size);
	}

	@Transactional(readOnly = true)
	public View get(Long id) {
		var c = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Category not found: " + id));
		return new View(c.getId(), c.getName(), c.getDescription(), c.getRestaurant().getId());
	}

	@Transactional
	public View update(Long id, Update req) {
		MenuCategory c = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Category not found: " + id));
		c.setName(req.name());
		c.setDescription(req.description());
		return new View(c.getId(), c.getName(), c.getDescription(), c.getRestaurant().getId());
	}

	@Transactional
	public void delete(Long id) {
		repo.deleteById(id);
	}
}
