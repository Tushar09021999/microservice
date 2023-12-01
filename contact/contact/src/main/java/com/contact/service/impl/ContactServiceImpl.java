package com.contact.service.impl;

import com.contact.entity.Contact;
import com.contact.payload.Product;
import com.contact.repository.ContactRepository;
import com.contact.service.ContactService;
import com.contact.payload.ContactResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepo;

    @Autowired
    private RestTemplate restTemplate;

    String url ="http://localhost:8083/api/product/";



    @Override
    public Contact saveContact(Contact contact) {
        return contactRepo.save(contact);
    }

    @Override
    public List<Contact> getAllContact() {
        return contactRepo.findAll();
    }

    @Override
    public void deleteContact(long id) {

    }

    @Override
    public Contact getContact(long id) {
        Contact contact = contactRepo.findById(id).get();
//        http://localhost:8083/api/product/1
        ArrayList<Product> products = restTemplate.getForObject(url + id, ArrayList.class);
        contact.setProducts(products);

        return contact;
    }

    @Override
    public ContactResponse getAllLead(int pageNo, int pageSize, String leadSource, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by("leadSource").ascending() : Sort.by("leadSource").descending();

        Pageable pageable = PageRequest.of(pageNo,pageSize, sort);
        Page<Contact> posts = contactRepo.findAll(pageable);
        List<Contact> contents = posts.getContent();
        ContactResponse  contactResponse =new ContactResponse();
        contactResponse.setContent(contents);
        contactResponse.setPageNo(posts.getNumber());
        contactResponse.setPageSize(posts.getSize());
        contactResponse.setTotalPages(posts.getTotalPages());
        contactResponse.setTotalElements(posts.getTotalElements());
        contactResponse.setLast(posts.isLast());


        return contactResponse;
    }




}
