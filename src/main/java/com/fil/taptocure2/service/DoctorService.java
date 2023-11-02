package com.fil.taptocure2.service;

import java.util.List;

import com.fil.taptocure2.model.Doctor;

public interface DoctorService {
	
	public List<Doctor> getAllDoctors();
	public Doctor getDoctorById(Long id);
	public Doctor createDoctor(Doctor doctor);
	public Doctor updateDoctor(Doctor doctor);
	public void deleteDoctor(Long id);

}
