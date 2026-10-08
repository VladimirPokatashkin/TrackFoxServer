package com.trackfox.server.trackfoxserver.dto;

import java.time.Instant;

public record TrainingDTO(
		long userId,
		Instant time,
		int duration,
		int averageHR,
		int maxHR
) {}