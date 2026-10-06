package com.trackfox.server.trackfoxserver.dto;

public record AthleteResponse(
		long id,
		long userId,
		String gender,
		int restHR
) {}