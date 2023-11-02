package com.fil.taptocure2.controller;

import java.util.List;

import com.fil.taptocure2.errorhandling.handlers.SessionExpired;
import com.fil.taptocure2.model.Doctor;
import com.fil.taptocure2.model.Patient;
import com.fil.taptocure2.repository.PatientRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.fil.taptocure2.dto.AddMedicalRecordDto;
import com.fil.taptocure2.model.MedicalRecord;
import com.fil.taptocure2.service.MedicalRecordService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
//@SessionAttributes("user1")
@RequestMapping("/api/v1")
public class MedicalRecordController {


    

    @Autowired
    private  MedicalRecordService medicalRecordService;

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/makerecordform")
    public String addMedicalRecords(){
        return "medicalrecordform";
    }

    @GetMapping("/getrecordform")
    public String getmedred(){
        return "MedRedList";
    }


    
    @GetMapping("/list")
    public String listMedicalRecords(Model model) {
        List<MedicalRecord> medicalRecords = medicalRecordService.getAllMedicalRecords();
        model.addAttribute("medicalRecords", medicalRecords);
        return "medicalrecordslist";
    }
    
    
//    @GetMapping("/create")
//    public String showAddMedicalRecordForm(Model model) {
//        AddMedicalRecordDto medicalRecordDto = new AddMedicalRecordDto();
//        model.addAttribute("medicalRecordDto", medicalRecordDto);
//        return "create-medicalrecord";
//    }

    

//    @PostMapping("/create")
//    public String createMedicalRecord(@ModelAttribute("medicalRecordDto") AddMedicalRecordDto medicalRecordDto) {
//        try {
//        	@PathVariable("patientId") int patientId;
//            @PathVariable("doctorId") int doctorId;
//            medicalRecordService.saveMedicalRecord(patientId, doctorId, medicalRecordDto);
//            System.out.println("medical record added");
//        } catch (Exception e) {
//        	System.out.println("not added");
//        }
//        return "create-medicalrecord";
//    }
@PostMapping("/create")
public ResponseEntity<String> createMedicalRecord(
        @RequestParam("patientEmail") String patientEmail,
        @RequestParam("medicalRecordDate") String medicalRecordDate,
        @RequestParam("medicalRecordDiagnosis") String medicalRecordDiagnosis,
        @RequestParam("medicalRecordDrugs") String medicalRecordDrugs,
        HttpSession session) throws Exception {
    if (session != null) {
        Object getAtt = session.getAttribute("doctor");
        if (getAtt instanceof Long) {
            // The attribute is already an integer, so you can directly cast it.

            Patient patient = patientRepository.findByPatientEmail(patientEmail);
            if(patient != null) {
                long patientId = patient.getPatientId();
                long doctorId = (long) getAtt;

                return medicalRecordService.saveMedicalRecord(patientId, doctorId, medicalRecordDate, medicalRecordDiagnosis, medicalRecordDrugs);
            }else{
                return ResponseEntity.badRequest().body("no patient");
            }
        }else {
//            System.out.println(did);
            System.out.println("no");
        }
    }
    throw new SessionExpired();

}

//    @PostMapping("/create")
//    public ResponseEntity<String> createMedicalRecord(
//            @RequestParam("patientId") int patientId,
//            @RequestParam("medicalRecordDate") String medicalRecordDate,
//            @RequestParam("medicalRecordDiagnosis") String medicalRecordDiagnosis,
//            @RequestParam("medicalRecordDrugs") String medicalRecordDrugs,
//            HttpSession session) throws Exception {
//        if(session != null){
//            Object doctor = session.getAttribute("user");
//            System.out.println(doctor);
////            return medicalRecordService.saveMedicalRecord(patientId, doctorId,medicalRecordDate,medicalRecordDiagnosis,medicalRecordDrugs );
//
//        }
//        return ResponseEntity.badRequest().body("login first");
//    }
    

    
    @PatchMapping("/update/{medicalRecordId}")
    public ResponseEntity<MedicalRecord> updateMedicalRecord(
            @PathVariable long medicalRecordId,
            @RequestBody MedicalRecord medicalRecord) {
        try {
            MedicalRecord updatedMedicalRecord = medicalRecordService.updateMedicalRecord(medicalRecordId, medicalRecord);
            return ResponseEntity.ok(updatedMedicalRecord);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/recordbyid/{medicalRecordId}")
    public MedicalRecord getMedicalRecordById(@PathVariable long medicalRecordId) {
        return medicalRecordService.getMedicalRecordById(medicalRecordId);
    }

    @DeleteMapping("/{medicalRecordId}")
    public void deleteMedicalRecord(@PathVariable long medicalRecordId) {
        medicalRecordService.deleteMedicalRecord(medicalRecordId);
    }

    @GetMapping("/patientrecord")
    public ResponseEntity<List<MedicalRecord>> getMedicalRecordsByPatientId(HttpSession session) {
        if (session != null) {
            Object getAtt = session.getAttribute("patient");
            if (getAtt instanceof Long) {
                // The attribute is already an integer, so you can directly cast it.
                long patientId = (long) getAtt;
                System.out.println(patientId);
                return ResponseEntity.ok().body(medicalRecordService.getMedicalRecordsByPatientId(patientId));
            }else {
//            System.out.println(did);
//                return "hi";
            }
        }
        throw new SessionExpired();
    }

    @GetMapping("/doctorrecord/{doctorId}")
    public List<MedicalRecord> getMedicalRecordsByDoctorId(@PathVariable long doctorId) {
        return medicalRecordService.getMedicalRecordsByDoctorId(doctorId);
    }

    @GetMapping("/recordofdiagnosis/{diagnosis}")
    public List<MedicalRecord> getMedicalRecordsWithDiagnosis(@PathVariable String diagnosis) {
        return medicalRecordService.getMedicalRecordsWithDiagnosis(diagnosis);
    }
    
    @GetMapping("/allrecords")
    public ResponseEntity<List<MedicalRecord>> getAllMedicalRecords() {
        List<MedicalRecord> medicalRecords = medicalRecordService.getAllMedicalRecords();
        return ResponseEntity.ok(medicalRecords);
    }

  
}