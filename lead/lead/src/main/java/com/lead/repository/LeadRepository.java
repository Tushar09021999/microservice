package com.lead.repository;

import com.lead.entity.Lead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeadRepository extends JpaRepository<Lead, Long> {

//    Page<Lead> findByLeadSource(String leadSource, Pageable pageable);
}
