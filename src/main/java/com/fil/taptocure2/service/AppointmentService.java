package com.fil.taptocure2.service;

import com.fil.taptocure2.dto.BookAppointmentDto;
import com.fil.taptocure2.model.Appointment;
import com.fil.taptocure2.model.MedicalRecord;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AppointmentService {
	
	ResponseEntity<String> bookAppointment(long doctorId, long patientId, String date, String healthProblem,String appointmentStatus) throws Exception;

    Appointment updateAppointmentStatus(long appointmentId, String status);

    List<Appointment> getAppointmentsByDoctorId(long doctorId);

    List<Appointment> getAppointmentsByPatientId(long patientId);

    List<Appointment> getAppointmentsByStatus(String status);

    void deleteAppointment(long appointmentId);
    
    List<Appointment> getAllAppointment();


}