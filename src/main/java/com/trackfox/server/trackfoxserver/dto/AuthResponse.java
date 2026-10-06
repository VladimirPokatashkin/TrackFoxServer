package com.trackfox.server.trackfoxserver.dto;

public record AuthResponse(
		long id,
		String name,
		String accessToken,
		String refreshToken
) {}