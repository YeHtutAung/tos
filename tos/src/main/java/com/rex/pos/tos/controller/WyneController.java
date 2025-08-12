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

import com.rex.pos.dto.WyneDTO.*;
import com.rex.pos.dto.common.PageResponse;
import com.rex.pos.tos.service.WyneService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tables")
@RequiredArgsConstructor
public class WyneController {

	private final WyneService service;

	@PostMapping("/wynes")
	public View create(@Valid @RequestBody Create req) {
		return service.create(req);
	}

	@GetMapping("/restaurants/{restaurantId}/wynes")
	public PageResponse<View> listByRestaurant(@PathVariable Long restaurantId,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
		return service.listByRestaurant(restaurantId, page, size);
	}

	@GetMapping("/wynes/{id}")
	public View get(@PathVariable Long id) {
		return service.get(id);
	}

	@PutMapping("/wynes/{id}")
	public View update(@PathVariable Long id, @Valid @RequestBody Update req) {
		return service.update(id, req);
	}

	@DeleteMapping("/wynes/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
}
