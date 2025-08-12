package com.rex.pos.dto;

import jakarta.validation.constraints.NotBlank;

public class RestaurantDTO {
	public record Create(@NotBlank String name) {
	}

	public record Update(@NotBlank String name) {
	}

	public record View(Long id, String name) {
	}
}
