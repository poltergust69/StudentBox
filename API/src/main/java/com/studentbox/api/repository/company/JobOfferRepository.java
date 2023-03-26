package com.studentbox.api.repository.company;

import com.studentbox.api.entities.company.job.JobOffer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer, UUID> {

    Page<JobOffer> findByCompanyUserUsername(String username, Pageable pageable);
}
