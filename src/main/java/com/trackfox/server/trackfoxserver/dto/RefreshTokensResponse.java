package com.trackfox.server.trackfoxserver.dto;

public record RefreshTokensResponse(
		String refreshToken,
		String accessToken
) {}