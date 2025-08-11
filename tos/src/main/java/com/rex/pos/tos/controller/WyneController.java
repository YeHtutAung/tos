package com.rex.pos.tos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rex.pos.dto.WyneDto;
import com.rex.pos.tos.service.WyneService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tables")
@RequiredArgsConstructor
public class WyneController {

	private final WyneService wyneService;
	
	@GetMapping("/{tableId}")
    public WyneDto getTable(@PathVariable Long tableId) {
        return wyneService.getTable(tableId);
    }
}
