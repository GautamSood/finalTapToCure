package com.fil.taptocure2.service;

import java.util.List;

import com.fil.taptocure2.model.Patient;



public interface PatientService {
	
	public List<Patient> getAllPatients();
	public Patient getPatientById(Long id);
	public Patient createPatient(Patient patient);
	public Patient updatePatient(Patient patient);
	public void deletePatient(Long id);

}
