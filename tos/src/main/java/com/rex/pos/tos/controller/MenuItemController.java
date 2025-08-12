/**
 * 
 */
package com.rex.pos.tos.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rex.pos.dto.MenuItemDTO.*;
import com.rex.pos.dto.common.PageResponse;
import com.rex.pos.tos.service.MenuItemService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * @author Rex
 *
 */
@RestController
@RequestMapping("/api/menu/")
@RequiredArgsConstructor
public class MenuItemController {
	private final MenuItemService service;

	@PostMapping("/items")
	public View create(@Valid @RequestBody Create req) {
		return service.create(req);
	}

	@GetMapping("/categories/{categoryId}/items")
	public PageResponse<View> listByCategory(@PathVariable Long categoryId, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size) {
		return service.listByCategory(categoryId, page, size);
	}

	@GetMapping("/items/{id}")
	public View get(@PathVariable Long id) {
		return service.get(id);
	}

	@PutMapping("/items/{id}")
	public View update(@PathVariable Long id, @Valid @RequestBody Update req) {
		return service.update(id, req);
	}

	@DeleteMapping("/items/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

}
