package com.lead.service;

import com.lead.entity.Lead;
import com.lead.payload.LeadResponse;

import java.util.List;

public interface LeadService {

    Lead saveLead(Lead lead);

    LeadResponse getAllLead(int pageNo, int pageSize, String leadSource, String sortDir);

    List<Lead> getLeads();

    void deleteLead(long id);

    Lead getLead(long id);
}
