package com.rex.pos.tos.service;

import com.rex.pos.models.Restaurant;
import com.rex.pos.models.Wyne;
import com.rex.pos.dto.WyneDTO.*;
import com.rex.pos.dto.common.PageResponse;
import com.rex.pos.tos.repository.RestaurantRepository;
import com.rex.pos.tos.repository.WyneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WyneService {

	 private final WyneRepository repo;
	    private final RestaurantRepository restaurantRepo;

	    @Transactional
	    public View create(Create req) {
	        Restaurant rest = restaurantRepo.findById(req.restaurantId())
	                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found: "+req.restaurantId()));
	        Wyne w = new Wyne();
	        w.setRestaurant(rest);
	        w.setWyneNumber(req.number());
	        if (req.status() != null) w.setStatus(Wyne.WyneStatus.valueOf(req.status().name()));
	        w.setQrCode(req.qrCode());
	        w = repo.save(w);
	        return new View(w.getId(), rest.getId(), w.getWyneNumber(), w.getStatus().name(), w.getQrCode());
	    }

	    @Transactional(readOnly = true)
	    public PageResponse<View> listByRestaurant(Long restaurantId, int page, int size) {
	        Page<Wyne> p = repo.findByRestaurantId(restaurantId, PageRequest.of(page, size));
	        return new PageResponse<>(p.map(w -> new View(w.getId(), w.getRestaurant().getId(), w.getWyneNumber(), w.getStatus().name(), w.getQrCode()))
	                .getContent(), p.getTotalElements(), p.getTotalPages(), page, size);
	    }

	    @Transactional(readOnly = true)
	    public View get(Long id) { var w = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Wyne not found: "+id));
	        return new View(w.getId(), w.getRestaurant().getId(), w.getWyneNumber(), w.getStatus().name(), w.getQrCode()); }

	    @Transactional
	    public View update(Long id, Update req) {
	        Wyne w = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Wyne not found: "+id));
	        w.setWyneNumber(req.number());
	        if (req.status() != null) w.setStatus(Wyne.WyneStatus.valueOf(req.status().name()));
	        w.setQrCode(req.qrCode());
	        return new View(w.getId(), w.getRestaurant().getId(), w.getWyneNumber(), w.getStatus().name(), w.getQrCode());
	    }

	    @Transactional
	    public void delete(Long id) { repo.deleteById(id); }
}
