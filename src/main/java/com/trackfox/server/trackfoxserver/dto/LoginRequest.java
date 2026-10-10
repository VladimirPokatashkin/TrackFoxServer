package com.trackfox.server.trackfoxserver.dto;

public record LoginRequest(
		String email,
		String password
) {}