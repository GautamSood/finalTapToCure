package com.fil.taptocure2.errorhandling.handlers;

@SuppressWarnings("serial")
public class MedicalRecordNotFound extends RuntimeException{
	
	public MedicalRecordNotFound() {
		super("Medical Record not found");
	}

}
