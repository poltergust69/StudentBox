package com.studentbox.api.repository;

import com.studentbox.api.entities.employmentInfo.EmploymentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmploymentInfoRepository extends JpaRepository<EmploymentInfo, UUID> {
}
