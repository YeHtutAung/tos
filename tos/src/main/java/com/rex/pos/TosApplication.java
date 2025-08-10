package com.rex.pos;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import com.rex.pos.tos.repository.MenuItemRepository;
import com.rex.pos.tos.repository.WyneRepository;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorProvider") // Enable auditing
public class TosApplication {

	@Autowired
	MenuItemRepository menuItemRepo;
	@Autowired
	WyneRepository wyneRepository;

	private static final Logger logger = LoggerFactory.getLogger(TosApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TosApplication.class, args);
	}

	@Bean
    public AuditorAware<String> auditorProvider() {
        return () -> Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getName)
                .or(() -> Optional.of("SYSTEM")); // Default if no user is logged in
    }
}
