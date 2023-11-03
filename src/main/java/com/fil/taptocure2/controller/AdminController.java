package com.fil.taptocure2.controller;

import com.fil.taptocure2.model.Admin;
import com.fil.taptocure2.model.Appointment;
import com.fil.taptocure2.model.Department;
import com.fil.taptocure2.model.Doctor;
import com.fil.taptocure2.model.HealthProblems;
import com.fil.taptocure2.model.MedicalRecord;
import com.fil.taptocure2.model.Patient;
import com.fil.taptocure2.service.AdminService;
import com.fil.taptocure2.service.DepartmentService;
import com.fil.taptocure2.service.DoctorService;
import com.fil.taptocure2.service.HealthProblemService;
import com.fil.taptocure2.service.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/admin")
public class AdminController {


    @Autowired
    private AdminService adminService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DepartmentService departmentService;
    
//    @Autowired
//    private MedicalRecordService medicalRecordService;
//    
//    @Autowired
//    private AppointmentService appointmentService;
    
    @Autowired
    private HealthProblemService healthProblemService;
    
    
    @GetMapping("/home")
    public String adminHome() {
        return "adminHome";
    }

    @GetMapping("/")
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @GetMapping("/{id}")
    public Admin getAdminById(@PathVariable long id) {
        return adminService.getAdminById(id).orElse(null);
    }

    @PostMapping("/")
    public Admin saveAdmin(@RequestBody Admin admin) {
    	
        return adminService.saveAdmin(admin);
    }

    @DeleteMapping("/{id}")
    public void deleteAdmin(@PathVariable long id) {
        adminService.deleteAdmin(id);
    }

    @GetMapping("/patients")
    public String getAllPatients(Model model) {
        List<Patient> patients = patientService.getAllPatients();
        model.addAttribute("patients", patients);
        return "patientList"; 
    }
    
    @GetMapping("/patients/delete/{id}")
    public String deletePatient(@PathVariable long id) {
        patientService.deletePatient(id);
        return "redirect:/api/v1/admin/patients"; 
    }

    
    @GetMapping("/doctors")
    public String getAllDoctors(Model model) {
        List<Doctor> doctors = doctorService.getAllDoctors();
        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("departments", departments);
        model.addAttribute("doctors", doctors);
        return "doctorList"; 
    }
    
    @GetMapping("/doctors/delete/{id}")
    public String deleteDoctor(@PathVariable long id) {
        doctorService.deleteDoctor(id);
        return "redirect:/api/v1/admin/doctors"; 
    }


//   
    @GetMapping("/departments")
    public String getAllDepartments(Model model) {
        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("departments", departments);
        return "department-list";
    }
    
//    @GetMapping("/medicalRecords")
//    public String getAllMedicalRecords(Model model) {
//        List<MedicalRecord> records = medicalRecordService.getAllMedicalRecords();
//        model.addAttribute("records", records);
//        return "medicalRecordList";
//    }
//    
//    @GetMapping("/appointments")
//    public String getAllAppointments(Model model) {
//        List<Appointment> appointment = appointmentService.getAllAppointment();
//        model.addAttribute("appointment", appointment);
//        return "appointmentList";
//    }
   
    
    @GetMapping("/healthProblems")
    public String viewHealthProblems(Model model) {
        List<HealthProblems> healthProblems = healthProblemService.getAllHealthProblems();
        model.addAttribute("healthProblems", healthProblems);
        return "healthProblemList";
    }
    
    @PostMapping("/assign_department_doctor/{department_id}/{doctor_id}")
    public ResponseEntity<String> assignDoctor(@PathVariable long department_id, @PathVariable long doctor_id){
        return healthProblemService.assignDoctor(department_id,doctor_id);
    }
	


}



