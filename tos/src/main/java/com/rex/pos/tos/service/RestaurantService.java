package com.rex.pos.tos.service;

import com.rex.pos.dto.RestaurantDTO.Create;
import com.rex.pos.dto.RestaurantDTO.Update;
import com.rex.pos.dto.RestaurantDTO.View;
import com.rex.pos.dto.common.PageResponse;
import com.rex.pos.models.Restaurant;
import com.rex.pos.tos.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RestaurantService {

	private final RestaurantRepository repo;

	@Transactional
	public View create(Create req) {
		Restaurant r = new Restaurant();
		r.setName(req.name());
		r = repo.save(r);
		return new View(r.getId(), r.getName());
	}

	@Transactional(readOnly = true)
	public PageResponse<View> list(int page, int size) {
		Page<Restaurant> p = repo.findAll(PageRequest.of(page, size));
		return new PageResponse<>(p.map(r -> new View(r.getId(), r.getName())).getContent(), p.getTotalElements(),
				p.getTotalPages(), page, size);
	}

	@Transactional(readOnly = true)
	public View get(Long id) {
		var r = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Restaurant not found: " + id));
		return new View(r.getId(), r.getName());
	}

	@Transactional
	public View update(Long id, Update req) {
		Restaurant r = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Restaurant not found: " + id));
		r.setName(req.name());
		return new View(r.getId(), r.getName());
	}

	@Transactional
	public void delete(Long id) {
		repo.deleteById(id);
	}
}
