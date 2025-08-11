package com.rex.pos.tos.service;

import org.springframework.stereotype.Service;

import com.rex.pos.dto.WyneDto;
import com.rex.pos.models.Wyne;
import com.rex.pos.tos.repository.WyneRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WyneService {

	private final WyneRepository wyneRepo;

	public WyneDto getTable(Long tableId) {
		Wyne t = wyneRepo.findById(tableId)
				.orElseThrow(() -> new IllegalArgumentException("Table not found: " + tableId));
		return new WyneDto(t.getId(), t.getWyneNumber(), t.getStatus().name(), t.getRestaurant().getId(),
				t.getQrCode());
	}
}
