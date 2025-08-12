package com.rex.pos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public class MenuItemDTO {
	public record Create(@NotNull Long categoryId, @NotBlank String name, String description,
			@NotNull @Positive BigDecimal price, Boolean available) {
	}

	public record Update(@NotBlank String name, String description, @NotNull @Positive BigDecimal price,
			Boolean available, Long categoryId) {
	}

	public record View(Long id, String name, String description, BigDecimal price, boolean available, Long categoryId) {
	}
}
