package com.rex.pos.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class WyneDTO {
	public enum WyneStatus {
		AVALIABLE, RESERVED, OCCUPIED, CLEARING
	}

	public record Create(@NotNull Long restaurantId, @NotNull @Positive Integer number, WyneStatus status, String qrCode) {
	}

	public record Update(@NotNull @Positive Integer number, WyneStatus status, String qrCode) {
	}

	public record View(Long id, Long restaurantId, Integer number, String status, String qrCode) {
	}
}
