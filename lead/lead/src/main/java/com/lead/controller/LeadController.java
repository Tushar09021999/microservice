package com.lead.controller;

import com.lead.entity.Lead;
import com.lead.payload.LeadResponse;
import com.lead.service.impl.LeadServiceImpl;
import com.lead.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LeadController {
    @Autowired
    private LeadServiceImpl leadService;


    @PostMapping("/lead")
    public Lead createLead(@Valid @RequestBody Lead lead){

        return leadService.saveLead(lead);
    }

    @GetMapping
    public List<Lead> getLeads(){
        return leadService.getLeads();
    }


    @GetMapping("/filtered-paginated")
    public LeadResponse getAll(@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                               @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                               @RequestParam(value = "leadSource", defaultValue = AppConstants.DEFAULT_FILTER_BY, required = false) String leadSource,
                               @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR, required = false) String sortDir)
    {
        LeadResponse Leads = leadService.getAllLead(pageNo, pageSize, leadSource, sortDir);
        return Leads;
    }

    @DeleteMapping("/{id}")
    public String deleteLeadById(@PathVariable Long id){
        leadService.deleteLead(id);
        return "lead deleted successfully";
    }


    @GetMapping("/lead/{id}")
    public  Lead getLeadById(@PathVariable long id){

        return leadService.getLead(id) ;
    }









































}
