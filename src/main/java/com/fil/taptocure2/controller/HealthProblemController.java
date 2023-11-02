package com.fil.taptocure2.controller;

import com.fil.taptocure2.model.Appointment;
import com.fil.taptocure2.model.Department;
import com.fil.taptocure2.model.Doctor;
import com.fil.taptocure2.model.HealthProblems;
import com.fil.taptocure2.repository.HealthProblemsRepository;
import com.fil.taptocure2.service.HealthProblemService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//@RestController
@Controller
@RequestMapping("/api/v1/healthproblems")
public class HealthProblemController {

    @Autowired
    private HealthProblemService healthProblemService;

    @Autowired
    private HealthProblemsRepository healthProblemsRepository;

    @GetMapping("/getall")
    public String getAllHealthProblem(){
       return "HealthProblem-list";
    }
    @GetMapping(value = "/getallproblems",produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<List<HealthProblems>> getProblems(){
        List<HealthProblems> healthProblemsList = healthProblemsRepository.findAll();
        return ResponseEntity.ok().body(healthProblemsList);
    }

    @GetMapping("/doctors/{healthId}")
    public String getDoctorsForHealthProblem(@PathVariable("healthId") long healthId, Model model) {
        try {
            HealthProblems healthProblems = healthProblemService.getHealthProblemById(healthId);
            model.addAttribute("healthProblemName", healthProblems.getHealthProblemName());
            model.addAttribute("doctors", healthProblems.getDepartment().getDoctors());
            return "DoctorperHealthissue";
        } catch (Exception e) {
            return "error";
        }
    }


    @PostMapping("/save")
    public HealthProblems saveHealthProblem(@RequestBody HealthProblems healthProblems){
        return healthProblemService.addHealthProblem(healthProblems);
    }
    @PostMapping("/assign_department/{department_id}/{health_id}")
    public ResponseEntity<String> assignDepartment(@PathVariable long department_id, @PathVariable long health_id) throws Exception{
        return healthProblemService.assignHealthProblem(department_id,health_id);
    }
    @PostMapping("/assign_department_doctor/{department_id}/{doctor_id}")
    public ResponseEntity<String> assignDoctor(@PathVariable long department_id, @PathVariable long doctor_id) throws Exception{
        return healthProblemService.assignDoctor(department_id,doctor_id);
    }


     @GetMapping("/doctorss/{healthId}")
      public ResponseEntity<List<Doctor>> getDoctors(@PathVariable long healthId) throws Exception {

      return healthProblemService.getDoctors(healthId); }




}

