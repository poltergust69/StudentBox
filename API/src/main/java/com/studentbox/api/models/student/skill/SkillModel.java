package com.studentbox.api.models.student.skill;

import com.studentbox.api.entities.student.skill.Skill;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class SkillModel {

    private UUID id;
    private String name;

    public SkillModel(Skill skill) {
        this.id = skill.getId();
        this.name = skill.getName();
    }
}
