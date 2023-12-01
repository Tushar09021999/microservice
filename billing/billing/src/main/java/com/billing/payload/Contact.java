package com.billing.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Column;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Contact {

    private String name;


    private String email;

    private String phoneNumber;
    @Transient
    private List<Product> products = new ArrayList<>();

    @Column(name ="lead_source")
    private  String leadSource;
}
