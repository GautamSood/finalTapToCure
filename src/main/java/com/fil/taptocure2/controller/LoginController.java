package com.fil.taptocure2.controller;

import com.fil.taptocure2.errorhandling.handlers.UserNotFoundException;
import com.fil.taptocure2.model.Doctor;
import com.fil.taptocure2.model.Patient;
import com.fil.taptocure2.repository.DoctorRepository;
import com.fil.taptocure2.repository.PatientRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@SessionAttributes("user1")
@RequestMapping("/api/v1/auth")
public class LoginController {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login_doctor")
    public String login_doctor(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session) throws Exception {
        Doctor doctor = doctorRepository.findByDoctorEmail(email);
        if(doctor != null){
            if(doctor.getPassword().equals(password)){
                session.setAttribute("doctor", doctor.getDoctorId());
                System.out.println("logged in");
//                Object doctorId1 = session.getAttribute("user");
//                System.out.println(doctorId1);
                return "login";
            }
            else {

                throw new UserNotFoundException();
            }
        }else {
            throw new UserNotFoundException();
        }

    }
    @PostMapping("/login_patient")
    public String login_patient(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session) throws Exception {
        Patient patient = patientRepository.findByPatientEmail(email);
        if(patient != null){
            if(patient.getPassword().equals(password)){
                session.setAttribute("patient", patient.getPatientId());
                System.out.println("logged in");
                return "login";
            }
            else {

                throw new UserNotFoundException();
            }
        }else {
            throw new UserNotFoundException();
        }

    }
    @GetMapping("/check")
    public String check(HttpSession session) {

        if (session != null) {
            Object getAtt = session.getAttribute("user");
            if (getAtt instanceof Long) {
                // The attribute is already an integer, so you can directly cast it.
                long did = (long) getAtt;
                System.out.println(did);
            }else {
//            System.out.println(did);
            return "hi";
            }
        }
        return "not present";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Check if a session is present
        if (session != null) {

            session.invalidate();

            return "redirect:/api/v1/auth/login";
        }
        return "not present";
    }
}
