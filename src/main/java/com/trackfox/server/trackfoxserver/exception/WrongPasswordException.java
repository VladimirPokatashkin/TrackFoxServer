package com.trackfox.server.trackfoxserver.exception;

public class WrongPasswordException extends RuntimeException {
	public WrongPasswordException(String message) {
		super(message);
	}
}