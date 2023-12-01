package com.contact.controller;

import com.contact.entity.Contact;
import com.contact.payload.Lead;
import com.contact.service.EmailService;
import com.contact.service.impl.ContactServiceImpl;
import com.contact.util.AppConstants;
import com.contact.payload.ContactResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ContactController {


    @Autowired
    private EmailService emailservice;

    @Autowired
    private ContactServiceImpl contactService;


    @Autowired
    private RestTemplate restTemplate;

    String baseUrl= "http://localhost:8080/api/lead/"   ;
    String  deleteUrl = "http://localhost:8080/api/";


    @PostMapping("/convert/{id}")
    public String converLead(@PathVariable Long id){
        Lead forObject = restTemplate.getForObject(baseUrl+id, Lead.class);
        Contact contact = new Contact();

        contact.setName(forObject.getName());
        contact.setEmail(forObject.getEmail());
        contact.setPhoneNumber(forObject.getPhoneNumber());
        contact.setLeadSource(forObject.getLeadSource());
        contactService.saveContact(contact);

        emailservice.sendSimpleMessage(contact.getEmail(),"Welcome","Thank you for choosing us, we're happy you're here ");


        restTemplate.delete(deleteUrl+id);


        return "Lead converted to contact and deleted";

    }



    @GetMapping
    public List<Contact> getContacts(){
        return contactService.getAllContact();

        }

    @GetMapping("/filtered-paginated")
    public ContactResponse getAll(@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                                  @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                  @RequestParam(value = "leadSource", defaultValue = AppConstants.DEFAULT_FILTER_BY, required = false) String leadSource,
                                  @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR, required = false) String sortDir)
    {
        ContactResponse Leads = contactService.getAllLead(pageNo, pageSize, leadSource, sortDir);
        return Leads;
    }


        @DeleteMapping("/contact/{id}")
        public  String deleteContact(@PathVariable long id){
        contactService.deleteContact(id);
        return "contact deleted successfully";
        }

        @GetMapping("/contact/{id}")
        public Contact getContactById(@PathVariable long id){
        return contactService.getContact(id);
        }
    }

