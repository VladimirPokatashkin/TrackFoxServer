package com.trackfox.server.trackfoxserver.dto;

public record LoginRequest(
		String name,
		String password
) {}