package com.contact.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Lead {

    private long id;

    private String name;


    private String email;

    private String phoneNumber;

    @Column(name ="lead_source")
    private  String leadSource;
}
