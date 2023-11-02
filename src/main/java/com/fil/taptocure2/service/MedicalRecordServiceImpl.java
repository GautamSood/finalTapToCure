package com.fil.taptocure2.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fil.taptocure2.dto.AddMedicalRecordDto;
import com.fil.taptocure2.model.Doctor;
import com.fil.taptocure2.model.MedicalRecord;
import com.fil.taptocure2.model.Patient;
import com.fil.taptocure2.repository.DoctorRepository;
import com.fil.taptocure2.repository.MedicalRecordRepository;
import com.fil.taptocure2.repository.PatientRepository;


@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {
	

	@Autowired
	private MedicalRecordRepository medicalRecordRepository;
	
	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	  @Override
	  public ResponseEntity<String> saveMedicalRecord(long patientId, long doctorId,String medicalRecordDate,String medicalRecordDiagnosis,String medicalRecordDrugs) throws Exception {
        Doctor doctor = doctorRepository.findById(doctorId).orElse(null);
        Patient patient = patientRepository.findById(patientId).orElse(null);
        LocalDate date1 = LocalDate.parse(medicalRecordDate);
        
//        if(medicalRecordRepository.findByDoctorAndPatientAndMedicalRecordDate(doctor, patient, date1).isPresent()){
//        	return ResponseEntity.badRequest().body("record already exists");
//        }

        MedicalRecord mr = new MedicalRecord();
        mr.setDoctor(doctor);
        mr.setMedicalRecordDate(date1);
        mr.setMedicalRecordDiagnosis(medicalRecordDate);
        mr.setMedicalRecordDrugs(medicalRecordDiagnosis);
        mr.setPatient(patient);
        
        List<MedicalRecord> mrlist = doctor.getMedicalRecords();
        
        mrlist.add(mr);
        
        doctor.setMedicalRecords(mrlist);
        doctorRepository.save(doctor);
        
        return ResponseEntity.ok().body(" added");
        
    }

    @Override
    public MedicalRecord updateMedicalRecord(long medicalRecordId, MedicalRecord medicalRecord) throws Exception {
        MedicalRecord existingRecord = medicalRecordRepository.findById(medicalRecordId).orElse(null);

        if (existingRecord == null) {
            throw new Exception("Medical record not found");
        }

        existingRecord.setMedicalRecordDate(medicalRecord.getMedicalRecordDate());
        existingRecord.setMedicalRecordDiagnosis(medicalRecord.getMedicalRecordDiagnosis());
        existingRecord.setMedicalRecordDrugs(medicalRecord.getMedicalRecordDrugs());

        return medicalRecordRepository.save(existingRecord);
    }

	@Override
	public MedicalRecord getMedicalRecordById(long medicalRecordId) {
		return medicalRecordRepository.findById(medicalRecordId).orElse(null);
    }

	@Override
	public void deleteMedicalRecord(long medicalRecordId) {
		 medicalRecordRepository.deleteById(medicalRecordId);
    }


	@Override
	public List<MedicalRecord> getMedicalRecordsByPatientId(long patientId) {
		return medicalRecordRepository.findByPatient_PatientId(patientId);
    }

	@Override
	public List<MedicalRecord> getMedicalRecordsByDoctorId(long doctorId) {
		 return medicalRecordRepository.findByDoctor_DoctorId(doctorId);
    }


	@Override
	public List<MedicalRecord> getMedicalRecordsWithDiagnosis(String medicalRecordDiagnosis) {
		return medicalRecordRepository.findByMedicalRecordDiagnosis(medicalRecordDiagnosis);
    }

	@Override
	public List<MedicalRecord> getAllMedicalRecords() {
		return medicalRecordRepository.findAll();
    }

	
    
}