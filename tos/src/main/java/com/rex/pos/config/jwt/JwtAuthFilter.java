package com.rex.pos.config.jwt;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthFilter extends OncePerRequestFilter {

	private final String secretKey;

	public JwtAuthFilter(String secretKey) {
		this.secretKey = secretKey;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String header = request.getHeader("Authorization");

		if (header == null || !header.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}

		String token = header.replace("Bearer ", "");
		try {
			Claims claims = Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes())).build()
					.parseSignedClaims(token).getPayload();

			String username = claims.getSubject();
			List<String> roles = claims.get("roles", List.class);

			Authentication auth = new UsernamePasswordAuthenticationToken(username, null,
					roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

			SecurityContextHolder.getContext().setAuthentication(auth);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		filterChain.doFilter(request, response);
	}
}
