package com.trackfox.server.trackfoxserver.dto;

public record AthleteDTO(
		long id,
		long userId,
		String gender,
		int restHR,
		double lactateCoef
) {}