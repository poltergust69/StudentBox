package com.studentbox.api.repository;

import com.studentbox.api.entities.education.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EducationRepository extends JpaRepository<Education, UUID> {

    Education findByName(String name);
}
