package com.rex.pos.tos.controller.jwt.auth;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rex.pos.config.jwt.JwtUtils;
import com.rex.pos.models.User;
import com.rex.pos.tos.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class JWTAuthController {

	private final JwtUtils jwtUtils;
	private final UserRepository userRepository;

	// Add this constructor manually for Eclipse to recognize injection
	public JWTAuthController(JwtUtils jwtUtils, UserRepository userRepository) {
		this.jwtUtils = jwtUtils;
		this.userRepository = userRepository;
	}

	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest request) {
		try {
			// 1. Validate refresh token
			if (!jwtUtils.validateToken(request.refreshToken())) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
			}

			// 2. Extract username and verify user exists
			String username = jwtUtils.getUsernameFromToken(request.refreshToken());
			Optional<User> user = userRepository.findByEmail(username);

			if (user.isEmpty()) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
			}

			// 3. Generate new tokens
			return ResponseEntity.ok(
					new TokenResponse(jwtUtils.generateAccessToken(username), jwtUtils.generateRefreshToken(username)));

		} catch (ResponseStatusException e) {
			throw e; // Re-throw explicit HTTP exceptions
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Token refresh failed: " + e.getMessage());
		}
	}

	// DTOs
	public record RefreshTokenRequest(String refreshToken) {
	}

	public record TokenResponse(String accessToken, String refreshToken) {
	}
}
