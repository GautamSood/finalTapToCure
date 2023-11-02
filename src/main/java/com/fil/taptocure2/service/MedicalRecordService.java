package com.fil.taptocure2.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.fil.taptocure2.dto.AddMedicalRecordDto;
import com.fil.taptocure2.model.MedicalRecord;

public interface MedicalRecordService {
	
	ResponseEntity<String>  saveMedicalRecord(long patientId,long doctorId,String medicalRecordDate,String medicalRecordDiagnosis,String medicalRecordDrugs) throws Exception;
	
	MedicalRecord updateMedicalRecord(long medicalRecordId, MedicalRecord medicalRecord) throws Exception;
	
    MedicalRecord getMedicalRecordById(long medicalRecordId);

    void deleteMedicalRecord(long medicalRecordId);
    
    List<MedicalRecord> getMedicalRecordsByPatientId(long patientId);

    List<MedicalRecord> getMedicalRecordsByDoctorId(long doctorId);

    List<MedicalRecord> getMedicalRecordsWithDiagnosis(String medicalRecordDiagnosis);

	List<MedicalRecord> getAllMedicalRecords();
}
