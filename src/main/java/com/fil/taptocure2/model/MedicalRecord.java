package com.fil.taptocure2.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "medical_record")
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="medicalrecord_id")
    private long medicalRecordId;

    @Column(name="medicalrecord_date")
    private LocalDate medicalRecordDate;

    @Column(name="medicalrecord_diagnosis")
//    @NotEmpty(message = "Medical Record diagnosis required")
//    @NotNull(message = "Medical Record diagnosis required")
//    @NotBlank(message = "Medical Record diagnosis required")
//    @Size(max=1800,message = "Maximum length of medical record diagnosis exceeded")
    private String medicalRecordDiagnosis;

    @Column(name="medicalrecord_drugs")
//    @NotEmpty(message = "Medical Record drugs required")
//    @NotNull(message = "Medical Record drugs required")
//    @NotBlank(message = "Medical Record drugs required")
//    @Size(max=700,message = "Maximum length of medical record drugs exceeded")
    private String medicalRecordDrugs;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name="doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

}
