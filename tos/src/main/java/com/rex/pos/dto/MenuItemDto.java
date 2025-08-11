package com.rex.pos.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Read-only payload for menu items returned to clients.
 * Use BigDecimal for money to avoid floating-point errors.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record MenuItemDto(
        Long id,
        String name,
        String description,
        BigDecimal price,
        boolean available,
        Long categoryId
) implements Serializable {}
