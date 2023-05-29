package com.studentbox.api.repository.company;

import com.studentbox.api.entities.company.Company;
import com.studentbox.api.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {
    Company findByName(String name);
    Optional<Company> findByUser(User user);
}
