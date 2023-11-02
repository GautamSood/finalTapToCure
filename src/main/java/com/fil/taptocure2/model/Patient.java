package com.fil.taptocure2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private long patientId;

    @Column(name = "first_name")
//    @Size(max=100,message = "Patient name length exceeded: 200")
//    @NotEmpty(message = "Doctor firstName required")
//    @NotBlank(message = "Doctor firstName required")
//    @NotNull(message = "Doctor firstName required")
    private String firstName;

    @Column(name = "last_name")
//    @Size(max=100,message = "Patient name length exceeded: 200")
//    @NotEmpty(message = "Doctor lastName required")
//    @NotBlank(message = "Doctor lastName required")
//    @NotNull(message = "Doctor lastName required")
    private String lastName;

    @Column(name = "patient_email")
//    @NotEmpty(message = "Doctor patientEmail required")
//    @NotBlank(message = "Doctor patientEmail required")
//    @NotNull(message = "Doctor patientEmail required")
    private String patientEmail;

    @Column(name="patient_contact")
//    @NotEmpty(message = "patientContact cannot be empty")
//    @NotBlank(message = "patientContact required")
//    @NotNull(message = "patientContact required")
//    @Size(max=12,message = "Patient name length exceeded: 12")
    private String patientContact;

    @Column(name="password")
    @Size(min=8,message = "Password must be minimum of 8 characters")
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name="patient_address")
//    @NotEmpty(message = "patientContact cannot be empty")
//    @NotBlank(message = "patientContact required")
//    @NotNull(message = "patientContact required")
//    @Size(max=400,message = "Patient address length exceeded: 400")
    private String patientAddress;

    @Column(name="patient_gender")
//    @NotEmpty(message = "Patient gender cannot be empty")
//    @NotBlank(message = "Patient gender required")
//    @NotNull(message = "Patient gender required")
//    @Size(max=12,message = "Patient gender length exceeded: 12")
    private String patientGender;

    @Column(name="patient_age")
//    @Min(value=1,message = "Patient patientAge invalid")
//    @NotEmpty(message = "patientAge cannot be empty")
//    @NotBlank(message = "patientAge required")
//    @NotNull(message = "patientAge required")
    private int patientAge;

    @OneToMany(mappedBy = "patient",fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "patient",fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List<MedicalRecord> medicalRecords;

}
