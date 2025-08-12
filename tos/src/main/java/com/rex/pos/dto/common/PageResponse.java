package com.rex.pos.dto.common;

import java.util.List;

/**
 * Common wrapper for paginated responses.
 * 
 * @param <T> content type
 */
public record PageResponse<T>(List<T> content, long totalElements, int totalPages, int page, int size) {
}
