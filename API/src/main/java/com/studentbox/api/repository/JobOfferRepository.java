package com.studentbox.api.repository;

import com.studentbox.api.entities.company.JobOffer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer, UUID> {

    Page<JobOffer> findByCompanyId(String id, Pageable pageable);
}
