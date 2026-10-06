package com.trackfox.server.trackfoxserver.exception;

public class AthleteNotFoundException extends RuntimeException {
	public AthleteNotFoundException(String message) {
		super(message);
	}
}