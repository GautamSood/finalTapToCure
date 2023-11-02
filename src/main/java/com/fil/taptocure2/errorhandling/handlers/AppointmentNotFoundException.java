package com.fil.taptocure2.errorhandling.handlers;

@SuppressWarnings("serial")
public class AppointmentNotFoundException extends RuntimeException{
	
	public AppointmentNotFoundException() {
		// TODO Auto-generated constructor stub
		super("Appointment not found");
	}

}
