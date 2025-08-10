package com.rex.pos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rex.pos.models.MenuItem;
import com.rex.pos.models.Wyne;
import com.rex.pos.tos.repository.MenuItemRepository;
import com.rex.pos.tos.repository.WyneRepository;

@SpringBootApplication
public class TosApplication implements CommandLineRunner {

	@Autowired
	MenuItemRepository menuItemRepo;
	@Autowired
	WyneRepository wyneRepository;

	private static final Logger logger = LoggerFactory.getLogger(TosApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		logger.info("Initializing Table Ordering System with sample data...");

		menuItemRepo.save(new MenuItem(null, "Burger", 9.99));
		menuItemRepo.save(new MenuItem(null, "Fries", 4.99));
		menuItemRepo.save(new MenuItem(null, "Coke", 2.99));

		wyneRepository.save(new Wyne(null, 1, "AVAILABLE"));
		wyneRepository.save(new Wyne(null, 2, "AVAILABLE"));

		logger.info("Startup data initialization complete.");
	}

}
