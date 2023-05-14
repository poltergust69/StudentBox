package com.studentbox.api.entities.employ;

import com.studentbox.api.entities.company.job.JobOffer;
import com.studentbox.api.entities.student.skill.Skill;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name="required_skills_for")
@NoArgsConstructor
public class JobSkill {

    @Id
    private UUID id;

    @OneToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;

    @OneToOne
    @JoinColumn(name = "job_id")
    private JobOffer jobOffer;

    public JobSkill(Skill skill, JobOffer jobOffer){
        this.id = UUID.randomUUID();
        this.skill = skill;
        this.jobOffer = jobOffer;
    }
}
