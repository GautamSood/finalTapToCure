package com.fil.taptocure2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fil.taptocure2.service.DoctorServiceImpl;
import com.fil.taptocure2.model.Doctor;

@Controller
@RequestMapping("/api/v1")
public class DoctorController {
	
		@Autowired
		private DoctorServiceImpl doctorService;

	@GetMapping("/doctor_landing")
	public String doctorLanding() {
		return "DoctorLanding";
	}

	@GetMapping("/createdoctor")
	public String doctorsignup(){
		return "SignupDoctor";
	}



	@GetMapping("/doctor")
		public List<Doctor> getAllDoctors(){
			return doctorService.getAllDoctors();
		}
		
		@GetMapping("/doctor/{id}")
		public Doctor getDoctorById(@PathVariable Long id) {
			return doctorService.getDoctorById(id);
		}
		
		@PostMapping("/doctor")
		public Doctor createDoctor(@RequestBody Doctor doctor) {
			return doctorService.createDoctor(doctor);
		}
		
		@PutMapping("/doctor")
		public Doctor updateDoctor(@RequestBody Doctor doctor) {
			return doctorService.updateDoctor(doctor);
		}
		
		@DeleteMapping("/doctor/{id}")
		public void deleteDoctor(@PathVariable Long id) {
			doctorService.deleteDoctor(id);
		}

}
