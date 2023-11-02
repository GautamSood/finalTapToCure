package com.fil.taptocure2.dto;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;



@Data
@NoArgsConstructor
public class AddMedicalRecordDto implements Serializable {
	String medicalRecordDate;
    String medicalRecordDiagnosis;
    String medicalRecordDrugs;
}
