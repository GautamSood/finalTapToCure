package com.fil.taptocure2.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fil.taptocure2.dto.BookAppointmentDto;
import com.fil.taptocure2.model.Appointment;
import com.fil.taptocure2.model.Doctor;
import com.fil.taptocure2.model.MedicalRecord;
import com.fil.taptocure2.repository.AppointmentRepository;
import com.fil.taptocure2.repository.DoctorRepository;
import com.fil.taptocure2.repository.PatientRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private  AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private  PatientRepository patientRepository;



   
    public ResponseEntity<String> bookAppointment(long doctorId, long patientId, String date, String healthProblem,
			String appointmentStatus) throws Exception {
        var doctor = doctorRepository.findById(doctorId).orElseThrow(()-> new Exception("no one found"));
        var patient = patientRepository.findById(patientId).orElseThrow(()-> new Exception("no patient found"));

//
//        if(appointmentRepository.findByDoctorAndPatient(doctor,patient).isPresent()) {
//            return ResponseEntity.badRequest().body("appointment already exists");
//        }


        LocalDate date1 = LocalDate.parse(date);
        // Create the appointment and save it
        Appointment appointment = new Appointment();
        appointment.setAppointmentStatus(appointmentStatus);
        appointment.setHealthProblem(healthProblem);
        appointment.setDate(date1);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);

        List<Appointment> appointments = doctor.getAppointments();

        appointments.add(appointment);
        doctor.setAppointments(appointments);
        doctorRepository.save(doctor);
        return ResponseEntity.ok().body("appointment added");
    }
 

    @Override
    public Appointment updateAppointmentStatus(long appointmentId, String status) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElse(null);
        if (appointment != null) {
            appointment.setAppointmentStatus(status);
            return appointmentRepository.save(appointment);
        }

        return null;
    }

    @Override
    public List<Appointment> getAppointmentsByDoctorId(long doctorId) {
        return appointmentRepository.findByDoctor_DoctorId(doctorId);
    }

    @Override
    public List<Appointment> getAppointmentsByPatientId(long patientId) {
        return appointmentRepository.findByPatient_PatientId(patientId);
    }

    @Override
    public List<Appointment> getAppointmentsByStatus(String status) {
        return appointmentRepository.findByAppointmentStatus(status);
    }


    @Override
    public void deleteAppointment(long appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }


	@Override
	public List<Appointment> getAllAppointment() {
		return appointmentRepository.findAll();
	}


	

}