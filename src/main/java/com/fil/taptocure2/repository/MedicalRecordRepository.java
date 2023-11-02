package com.fil.taptocure2.repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fil.taptocure2.dto.AddMedicalRecordDto;
import com.fil.taptocure2.model.Doctor;
import com.fil.taptocure2.model.MedicalRecord;
import com.fil.taptocure2.model.Patient;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {

	List<MedicalRecord> findByPatient_PatientId(long patientId);

	List<MedicalRecord> findByDoctor_DoctorId(long doctorId);
	
    Optional<Object> findByDoctorAndPatientAndMedicalRecordDate(Doctor doctor, Patient patient, LocalDate medicalRecordDate );

	List<MedicalRecord> findByMedicalRecordDiagnosis(String medicalRecordDiagnosis);
    
}