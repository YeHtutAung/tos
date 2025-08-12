package com.rex.pos.tos.service;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rex.pos.dto.MenuItemDTO.Create;
import com.rex.pos.dto.MenuItemDTO.Update;
import com.rex.pos.dto.MenuItemDTO.View;
import com.rex.pos.dto.common.PageResponse;
import com.rex.pos.models.MenuCategory;
import com.rex.pos.models.MenuItem;
import com.rex.pos.tos.repository.MenuCategoryRepository;
import com.rex.pos.tos.repository.MenuItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuItemService {

	private final MenuItemRepository repo;
	private final MenuCategoryRepository categoryRepo;

	@Transactional
	public View create(Create req) {
		MenuCategory cat = categoryRepo.findById(req.categoryId())
				.orElseThrow(() -> new IllegalArgumentException("Category not found: " + req.categoryId()));
		MenuItem i = new MenuItem();
		i.setCategory(cat);
		i.setName(req.name());
		i.setDescription(req.description());
		// assuming entity price is BigDecimal; if it's double, use
		// req.price().doubleValue()
		try {
			i.setPrice(req.price());
		} catch (Exception e) {
			i.setPrice(BigDecimal.valueOf(req.price().doubleValue()));
		}
		if (req.available() != null)
			i.setAvailable(req.available());
		i = repo.save(i);
		return new View(i.getId(), i.getName(), i.getDescription(),
				(i.getPrice() instanceof BigDecimal bd) ? bd
						: BigDecimal.valueOf(Double.parseDouble(i.getPrice().toString())),
				i.isAvailable(), i.getCategory().getId());
	}

	@Transactional(readOnly = true)
	public PageResponse<View> listByCategory(Long Id, int page, int size) {
		Page<MenuItem> p = repo.findByCategoryId(Id, PageRequest.of(page, size));
		return new PageResponse<>(
				p.map(i -> new View(i.getId(), i.getName(), i.getDescription(),
						(i.getPrice() instanceof BigDecimal bd) ? bd : new BigDecimal(i.getPrice().toString()),
						i.isAvailable(), i.getCategory().getId())).getContent(),
				p.getTotalElements(), p.getTotalPages(), page, size);
	}

	@Transactional(readOnly = true)
	public View get(Long id) {
		var i = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Item not found: " + id));
		var price = (i.getPrice() instanceof BigDecimal bd) ? bd : new BigDecimal(i.getPrice().toString());
		return new View(i.getId(), i.getName(), i.getDescription(), price, i.isAvailable(), i.getCategory().getId());
	}

	@Transactional
	public View update(Long id, Update req) {
		MenuItem i = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Item not found: " + id));
		if (req.categoryId() != null) {
			MenuCategory cat = categoryRepo.findById(req.categoryId())
					.orElseThrow(() -> new IllegalArgumentException("Category not found: " + req.categoryId()));
			i.setCategory(cat);
		}
		i.setName(req.name());
		i.setDescription(req.description());
		try {
			i.setPrice(req.price());
		} catch (Exception e) {
			i.setPrice(BigDecimal.valueOf(req.price().doubleValue()));
		}
		if (req.available() != null)
			i.setAvailable(req.available());
		return new View(i.getId(), i.getName(), i.getDescription(),
				(i.getPrice() instanceof BigDecimal bd) ? bd : new BigDecimal(i.getPrice().toString()), i.isAvailable(),
				i.getCategory().getId());
	}

	@Transactional
	public void delete(Long id) {
		repo.deleteById(id);
	}
}
