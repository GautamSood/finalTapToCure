package com.fil.taptocure2.controller;

import com.fil.taptocure2.errorhandling.handlers.SessionExpired;
import com.fil.taptocure2.model.Appointment;
import com.fil.taptocure2.service.AppointmentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1")
public class AppointmentController {
	
	
	
	@Autowired
    private  AppointmentService appointmentService;

    @GetMapping("/getApp")
    public String getmedred(){
        return "GetAppoint";
    }

    @GetMapping("/getApp2")
    public String getmedred2(){
        return "DoctorAppointment";
    }

    @GetMapping("/getMed")
    public String getmedred3(){
        return "MediacalRecordsDoctor";
    }




    @GetMapping("/patientappointment")
    public ResponseEntity<List<Appointment>> getAppointmentsByPatientId(HttpSession session) {
        if (session != null) {
            Object getAtt = session.getAttribute("patient");
            System.out.println(session.getAttribute("patient"));
            if (getAtt instanceof Long) {
                // The attribute is already an integer, so you can directly cast it.
                long patientId = (long) getAtt;
                List<Appointment> ls = appointmentService.getAppointmentsByPatientId(patientId);
                return ResponseEntity.ok().body(ls);
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } else {
//            System.out.println(did);
            System.out.println("no");
        }

        throw new SessionExpired();
    }
    @PostMapping("/book/{doctorId}")
    public ResponseEntity<String> bookAppointment(
            @PathVariable("doctorId") long doctorId,
            @RequestParam("date") String date,
            @RequestParam("healthProblem") String healthProblem,
            @RequestParam("appointmentStatus") String appointmentStatus,
            HttpSession session) throws Exception {

        if (session != null) {
            Object getAtt = session.getAttribute("patient");
            System.out.println(session.getAttribute("patient"));
            if (getAtt instanceof Long) {
                // The attribute is already an integer, so you can directly cast it.
                long patientId = (long) getAtt;
                return appointmentService.bookAppointment(doctorId,patientId,date,healthProblem,appointmentStatus);
                }else{
                    return ResponseEntity.badRequest().body("no patient");
                }
            }else {
//            System.out.println(did);
                System.out.println("no");
            }

        throw new SessionExpired();
    }

    @PatchMapping("/updateappointmentstatus/{appointmentId}")
    public Appointment updateAppointmentStatus(@PathVariable long appointmentId, @RequestParam String status) {
        return appointmentService.updateAppointmentStatus(appointmentId, status);
    }

    @GetMapping("/doctoappointment/{doctorId}")
    public List<Appointment> getAppointmentsByDoctorId(@PathVariable long doctorId) {
        return appointmentService.getAppointmentsByDoctorId(doctorId);
    }



    @GetMapping("/appontmentstatus/{status}")
    public List<Appointment> getAppointmentsByStatus(@PathVariable String status) {
        return appointmentService.getAppointmentsByStatus(status);
    }

    @DeleteMapping("/{appointmentId}")
    public void deleteAppointment(@PathVariable long appointmentId) {
        appointmentService.deleteAppointment(appointmentId);
    }
    
    @GetMapping("/allappointments")
    public ResponseEntity<List<Appointment>> getAllAppointment() {
    	List<Appointment> appointments = appointmentService.getAllAppointment();
        return ResponseEntity.ok(appointments);
    }

}