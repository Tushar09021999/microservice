package com.contact.service;

import com.contact.entity.Contact;
import com.contact.payload.ContactResponse;

import java.util.List;

public interface ContactService {

    Contact saveContact(Contact contact);

    List<Contact> getAllContact();
    ContactResponse getAllLead(int pageNo, int pageSize, String leadSource, String sortDir);

    void deleteContact(long id);

    Contact getContact(long id);
}
