package com.fil.taptocure2.repository;

import com.fil.taptocure2.model.Appointment;
import com.fil.taptocure2.model.Doctor;
import com.fil.taptocure2.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {


    List<Appointment> findByDoctor_DoctorId(long doctorId);

    List<Appointment> findByPatient_PatientId(long patientId);

    List<Appointment> findByAppointmentStatus(String status);

    Optional<Object> findByDoctorAndPatient(Doctor doctor, Patient patient);
}