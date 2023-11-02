package com.fil.taptocure2.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;


@Value
@Data
@Builder
public class BookAppointmentDto implements Serializable {
    String date;
    String healthProblem;
    String appointmentStatus;
}