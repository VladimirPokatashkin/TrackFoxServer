package com.trackfox.server.trackfoxserver.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TokenService {
	private String issuer;
	private String secret;

	public String generateAccessToken(long id) {
		return JWT.create()
				.withIssuer(issuer)
				.withSubject(String.valueOf(id))
				.withExpiresAt(Date.from(Instant.now().plusSeconds(3600)))
				.sign(Algorithm.HMAC256(secret));
	}

	public String generateRefreshToken() {
		return UUID.randomUUID().toString();
	}
}