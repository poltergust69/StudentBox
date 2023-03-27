package com.studentbox.api.entities.student.skill;

import com.studentbox.api.models.student.skill.SkillCreationModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@Table(name="skills")
@NoArgsConstructor
public class Skill {

    @Id
    private UUID id;

    @Column(length = 100)
    private String name;

    public Skill(SkillCreationModel skillCreationModel) {
        this.id = UUID.randomUUID();
        this.name = skillCreationModel.getName();
    }
}
