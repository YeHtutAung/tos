/**
 * 
 */
package com.rex.pos.tos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rex.pos.models.MenuItem;
import com.rex.pos.tos.repository.MenuItemRepository;

/**
 * @author Rex
 *
 */
@RestController
@RequestMapping("/api/menu")
public class MenuController {

	@Autowired
	private MenuItemRepository menuRepo;

	@GetMapping
	public List<MenuItem> getMenu() {
		return menuRepo.findAll();
	}
}
