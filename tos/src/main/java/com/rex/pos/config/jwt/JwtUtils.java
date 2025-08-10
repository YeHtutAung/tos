package com.rex.pos.config.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

	@Value("${jwt.secret-key}")
	private String secretKey;

	@Value("${jwt.access-token.expiration}")
	private int accessTokenExpirationMs;

	@Value("${jwt.refresh-token.expiration}")
	private int refreshTokenExpirationMs;

	// Generate ACCESS token
	public String generateAccessToken(String username) {
		return buildToken(username, accessTokenExpirationMs);
	}

	// Generate REFRESH token (longer-lived, fewer claims)
	public String generateRefreshToken(String username) {
		return buildToken(username, refreshTokenExpirationMs);
	}

	private String buildToken(String username, int expirationMs) {
		return Jwts.builder().subject(username).issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + expirationMs))
				.signWith(Keys.hmacShaKeyFor(secretKey.getBytes())).compact();
	}

	// Validate any token
	public boolean validateToken(String token) {
		try {
			Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes())).build().parseSignedClaims(token);
			return true;
		} catch (JwtException e) {
			return false;
		}
	}

	public String generateToken(String token) {
		return Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes())).build().parseSignedClaims(token)
				.getPayload().getSubject();
	}
}
