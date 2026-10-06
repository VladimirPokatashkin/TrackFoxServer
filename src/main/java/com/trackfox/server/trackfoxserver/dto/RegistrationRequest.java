package com.trackfox.server.trackfoxserver.dto;

public record RegistrationRequest(
		String email,
		String name,
		String password
) {}