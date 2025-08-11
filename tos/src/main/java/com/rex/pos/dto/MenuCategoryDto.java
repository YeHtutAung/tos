package com.rex.pos.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Read-only payload for menu category returned to clients.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record MenuCategoryDto(
		Long id, 
		String name, 
		String description, 
		List<MenuItemDto> items
) implements Serializable {} 