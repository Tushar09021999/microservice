package com.billing.service;

import com.billing.entity.Billing;
import com.billing.payload.Contact;
import com.billing.payload.Product;
import com.billing.repository.BillingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class Billingservice {

    Logger logger = LoggerFactory.getLogger(Billingservice.class);

    @Autowired
    private RestTemplate restTemplate;

    String baseurl = "http://localhost:8081/api/contact/";


    @Autowired
    private BillingRepository billingRepo;

    public Billing generateInvoice(long id) {
        Contact contact = restTemplate.getForObject(baseurl + id, Contact.class);

        Billing billing= new Billing();
        billing.setName(contact.getName());
        billing.setEmail(contact.getEmail());
        billing.setPhoneNumber(contact.getPhoneNumber());
        billing.setProducts(contact.getProducts());

        double totalAmount = billing.calculateTotalAmount();

        billing.setTotalAmount(totalAmount);
        billingRepo.save(billing);

        return billing;

    }


}
