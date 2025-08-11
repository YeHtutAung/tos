package com.rex.pos.tos.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import com.rex.pos.dto.WyneDto;
import com.rex.pos.tos.service.WyneService;

@WebMvcTest(WyneController.class)
@AutoConfigureMockMvc(addFilters = false)
class WyneControllerTest {

	@Autowired
    private MockMvc mockMvc;

	@MockitoBean
    private WyneService wyneService;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
    void getTable_returnsWyneDto_whenFound() throws Exception {
        // Arrange
        WyneDto dto = new WyneDto(10L, 7, "AVALIABLE", 1L, "QR-XYZ");
        given(wyneService.getTable(10L)).willReturn(dto);

        // Act + Assert
        mockMvc.perform(get("/api/tables/{tableId}", 10L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                // JSON fields based on WyneDto(record) property names
                .andExpect(jsonPath("$.id").value(10L))
                .andExpect(jsonPath("$.tableNumber").value(7))
                .andExpect(jsonPath("$.status").value("AVALIABLE"))
                .andExpect(jsonPath("$.restaurantId").value(1L))
                .andExpect(jsonPath("$.qrCode").value("QR-XYZ"));
    }

	@Test
    void getTable_returnsServerError_whenServiceThrowsIllegalArgumentException() throws Exception {
        // No global @ControllerAdvice provided, so expect 500 by default
        given(wyneService.getTable(anyLong()))
                .willThrow(new IllegalArgumentException("Table not found: 99"));

        mockMvc.perform(get("/api/tables/{tableId}", 99L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }
}
