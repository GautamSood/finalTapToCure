package com.fil.taptocure2.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name="admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="admin_id")
    private long adminId;

    @Column(name="admin_email")
    @Email(message = "Not a valid email")
    @NotEmpty(message = "Admin email required")
    @Size(max=200,message = "Email length exceeded: 200")
    @NotNull
    private String adminEmail;




    @Column(name="password")
    @Size(min=8,message = "Password must be minimum of 8 characters")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name="admin_name")
    @NotEmpty(message = "Admin name cannot be empty")
    @Size(max=200,message = "Admin name length exceeded: 200")
    private String adminName;

    @Column(name="admin_contact")
    @NotEmpty(message = "Admin contact cannot be empty")
    @Size(max=12,message = "Admin name length exceeded: 12")
    private String adminContact;
}