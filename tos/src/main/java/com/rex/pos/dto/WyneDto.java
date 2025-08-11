package com.rex.pos.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record WyneDto(
		Long id,
        Integer tableNumber,
        String status,
        Long restaurantId,
        String qrCode
) implements Serializable {}
