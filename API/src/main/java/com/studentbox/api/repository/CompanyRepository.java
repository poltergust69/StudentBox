package com.studentbox.api.repository;

import com.studentbox.api.entities.company.Company;
import com.studentbox.api.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, User> {

    Company findByName(String name);
}
