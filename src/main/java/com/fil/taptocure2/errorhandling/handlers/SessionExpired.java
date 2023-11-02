package com.fil.taptocure2.errorhandling.handlers;

public class SessionExpired extends RuntimeException{
    public SessionExpired() {
        // TODO Auto-generated constructor stub
        super("session expired");
    }
}
