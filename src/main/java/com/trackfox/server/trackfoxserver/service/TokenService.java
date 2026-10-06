package com.trackfox.server.trackfoxserver.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.trackfox.server.trackfoxserver.exception.InvalidTokenException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class TokenService {
	private final String issuer;
	private final Algorithm algorithm;
	private final JWTVerifier verifier;


	public TokenService(String issuer, String secret) {
		this.issuer = issuer;
		algorithm = Algorithm.HMAC256(secret);
		verifier = JWT.require(algorithm).withIssuer(issuer).build();
	}

	public String generateAccessToken(long id) {
		return JWT.create()
				.withIssuer(issuer)
				.withSubject(String.valueOf(id))
				.withExpiresAt(Date.from(Instant.now().plusSeconds(3600)))
				.sign(algorithm);
	}

	public String generateRefreshToken() {
		return UUID.randomUUID().toString();
	}

	public long getUserIdFromToken(String token) {
		try {
			DecodedJWT jwt = verifier.verify(token);
			return Long.parseLong(jwt.getSubject());
		} catch (Exception ex) {
			throw new InvalidTokenException("invalid token.");
		}
	}
}