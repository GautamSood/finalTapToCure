package com.fil.taptocure2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fil.taptocure2.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{
    Doctor findByDoctorEmail(String email);
}
