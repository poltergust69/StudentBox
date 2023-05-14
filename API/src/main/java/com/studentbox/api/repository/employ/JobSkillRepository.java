package com.studentbox.api.repository.employ;

import com.studentbox.api.entities.company.job.JobOffer;
import com.studentbox.api.entities.employ.JobSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JobSkillRepository extends JpaRepository<JobSkill, UUID> {

    List<JobSkill> findAllByJobOffer(JobOffer jobOffer);


}
