package com.rex.pos.tos.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.rex.pos.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
}
