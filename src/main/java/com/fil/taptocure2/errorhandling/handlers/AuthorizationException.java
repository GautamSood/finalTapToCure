package com.fil.taptocure2.errorhandling.handlers;

@SuppressWarnings("serial")
public class AuthorizationException extends RuntimeException {

	public AuthorizationException() {
		super("Authorization failed");
	}
}
