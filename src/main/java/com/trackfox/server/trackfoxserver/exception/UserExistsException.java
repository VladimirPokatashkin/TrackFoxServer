package com.trackfox.server.trackfoxserver.exception;

public class UserExistsException extends RuntimeException {
	public UserExistsException(String message) {
		super(message);
	}
}