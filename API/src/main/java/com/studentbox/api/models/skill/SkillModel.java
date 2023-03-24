package com.studentbox.api.models.skill;

import com.studentbox.api.entities.skill.Skill;
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
