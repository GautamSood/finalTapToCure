package com.fil.taptocure2.service;
import com.fil.taptocure2.model.Department;
import com.fil.taptocure2.model.Doctor;
import com.fil.taptocure2.model.HealthProblems;
import com.fil.taptocure2.repository.DepartmentRepository;
import com.fil.taptocure2.repository.DoctorRepository;
import com.fil.taptocure2.repository.HealthProblemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthProblemServiceImpl implements HealthProblemService {

    @Autowired
    private HealthProblemsRepository healthProblemsRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public HealthProblems addHealthProblem(HealthProblems healthProblems) {
        return healthProblemsRepository.save(healthProblems);
    }

    @Override
    public ResponseEntity<String> assignHealthProblem(long departmentId, long healthId) throws Exception {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new Exception());
        HealthProblems healthProblems = healthProblemsRepository.findById(healthId).orElseThrow(()-> new Exception());

        healthProblems.setDepartment(department);
        healthProblemsRepository.save(healthProblems);
        return ResponseEntity.ok("health added");
    }

    @Override
    public ResponseEntity<String> assignDoctor(long departmentId, long doctor_id) throws Exception {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new Exception());
        Doctor doctor = doctorRepository.findById(doctor_id).orElseThrow(() -> new Exception());

        doctor.setDepartment(department);
        doctorRepository.save(doctor);
        return ResponseEntity.ok("doctor mapped");
    }

    @Override
    public ResponseEntity<List<Doctor>> getDoctors(long healthId) throws Exception {
        HealthProblems healthProblems = healthProblemsRepository.findById(healthId).orElseThrow(()-> new Exception());
       Department department = healthProblems.getDepartment();
       return ResponseEntity.ok(department.getDoctors());
    }
    
    @Override
    public List<HealthProblems> getAllHealthProblems() {
        return healthProblemsRepository.findAll();
    }

	@Override
	public HealthProblems getHealthProblemById(long healthId) throws Exception {
		 return healthProblemsRepository.findById(healthId).orElseThrow(() -> new Exception("Health problem not found"));
    }

	}

	

