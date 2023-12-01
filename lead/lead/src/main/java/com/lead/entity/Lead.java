package com.lead.entity;

import lombok.AllArgsConstructor;
import lombok.*;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.validation.constraints.Max;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Lead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Name is mandatory")
    @NotBlank(message = "Name must not be empty")
    private String name;

    @NotNull(message = "Email is mandatory")
    @NotBlank(message = "Email must not be empty")
    @Email(message = "Invalid Email Format")
    private String email;

    @NotNull(message = "PhoneNumber is mandatory")
    @NotBlank(message = "PhoneNumber must not be empty")
    @Size(min=10,max=10, message = "phoneNumber must be 10 digits")
    private String phoneNumber;

    @Column(name ="lead_source")
    private  String leadSource;

}
