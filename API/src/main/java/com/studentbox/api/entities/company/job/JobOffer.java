package com.studentbox.api.entities.company.job;

import com.studentbox.api.entities.company.Company;
import com.studentbox.api.entities.jobposition.JobPosition;
import com.studentbox.api.entities.student.skill.Skill;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name="job_offers")
@NoArgsConstructor
public class JobOffer {

    @Id
    private UUID id;

    @JoinColumn(name="company_id")
    @ManyToOne
    private Company company;

    @JoinColumn(name="position_id")
    @ManyToOne
    private JobPosition jobPosition;

    private String description;

    @JoinColumn(name="monthly_salary_in_euros")
    private Long salary;

    @Column(name="created_at")
    private Timestamp createdAt;

    @Column(name="modified_at")
    private Timestamp modifiedAt;


    @ManyToMany
    @JoinTable(name="job_offer_skills",
            joinColumns = @JoinColumn(name="job_id"),
            inverseJoinColumns = @JoinColumn(name="skill_id"))
    List<Skill> skills;
}
