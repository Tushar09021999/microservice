package com.lead.service.impl;

import com.lead.entity.Lead;
import com.lead.exception.ResourceNotFoundException;
import com.lead.payload.LeadResponse;
import com.lead.repository.LeadRepository;
import com.lead.service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeadServiceImpl implements LeadService {


    @Autowired
    private LeadRepository leadRepo;

    @Override
    public Lead saveLead(Lead lead) {
        return leadRepo.save(lead);
    }

    @Override
    public LeadResponse getAllLead(int pageNo, int pageSize,String leadSource,String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(leadSource).ascending() : Sort.by(leadSource).descending();

        Pageable pageable = PageRequest.of(pageNo,pageSize, sort);
        Page<Lead> posts = leadRepo.findAll(pageable);
        List<Lead> contents = posts.getContent();
        LeadResponse  leadResponse =new LeadResponse();
        leadResponse.setContent(contents);
        leadResponse.setPageNo(posts.getNumber());
        leadResponse.setPageSize(posts.getSize());
        leadResponse.setTotalPages(posts.getTotalPages());
        leadResponse.setTotalElements(posts.getTotalElements());
        leadResponse.setLast(posts.isLast());


        return leadResponse;
    }

    @Override
    public List<Lead> getLeads() {
        return leadRepo.findAll();
    }

    @Override
    public void deleteLead(long id) {
        Lead lead = leadRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Lead with id: " + id + " not found"));

            leadRepo.deleteById(id);

    }

    @Override
    public Lead getLead(long id) {
        Lead lead = leadRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Lead with id: " + id + " not found"));
        return lead;
    }
}
