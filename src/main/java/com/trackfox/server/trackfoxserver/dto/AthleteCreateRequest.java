package com.trackfox.server.trackfoxserver.dto;

public record AthleteCreateRequest(
		long userId,
		String gender,
		int restHR
) {}