package com.studentbox.api.repository.jobposition;

import com.studentbox.api.entities.jobposition.JobPosition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JobPositionRepository extends JpaRepository<JobPosition, UUID> {

    @Override
    Page<JobPosition> findAll(Pageable pageable);

    JobPosition findByName(String name);

}
