package com.rex.pos.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MenuCategoryDTO {
	public record Create(@NotNull Long restaurantId, @NotBlank String name, String description) {
	}

	public record Update(@NotBlank String name, String description) {
	}

	public record View(Long id, String name, String description, Long restaurantId) {
	}
}