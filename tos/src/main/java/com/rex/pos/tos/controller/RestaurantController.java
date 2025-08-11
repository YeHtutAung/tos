package com.rex.pos.tos.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rex.pos.dto.MenuCategoryDto;
import com.rex.pos.tos.service.MenuCategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

	private final MenuCategoryService menuCategoryService;
	
	@GetMapping("/{id}/menu")
    public List<MenuCategoryDto> getMenu(@PathVariable("id") Long restaurantId) {
        return menuCategoryService.getMenuByRestaurant(restaurantId);
    }
}
