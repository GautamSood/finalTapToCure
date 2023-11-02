package com.fil.taptocure2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fil.taptocure2.repository.DoctorRepository;
import com.fil.taptocure2.service.DoctorService;
import com.fil.taptocure2.model.Doctor;

@Service
public class DoctorServiceImpl implements DoctorService{
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Override
	public List<Doctor> getAllDoctors() {
		return doctorRepository.findAll();
	}

	@Override
	public Doctor getDoctorById(Long id) {
		return doctorRepository.findById(id).orElse(null);
	}

	@Override
	public Doctor createDoctor(Doctor doctor) {
		return doctorRepository.save(doctor);
	}

	@Override
	public Doctor updateDoctor(Doctor doctor) {
		return doctorRepository.save(doctor);
	}

	@Override
	public void deleteDoctor(Long id) {
		doctorRepository.deleteById(id);
	}

}