package com.fil.taptocure2.service;

import com.fil.taptocure2.model.Admin;
import com.fil.taptocure2.model.Doctor;
import com.fil.taptocure2.model.HealthProblems;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface HealthProblemService {
    public HealthProblems addHealthProblem(HealthProblems healthProblems);
    ResponseEntity<String> assignHealthProblem(long departmentId, long healthId) throws Exception;
    ResponseEntity<String> assignDoctor(long departmentId, long doctorId) throws Exception;
    ResponseEntity<List<Doctor>> getDoctors(long healthId) throws Exception;
    public List<HealthProblems> getAllHealthProblems();
    public HealthProblems getHealthProblemById(long healthId) throws Exception;
}