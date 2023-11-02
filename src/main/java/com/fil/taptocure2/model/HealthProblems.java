package com.fil.taptocure2.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "health_problems")
public class HealthProblems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "health_problem_id")
    private long healthProblemid;

    @Column(name="health_problem_name")
    private String healthProblemName;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

}