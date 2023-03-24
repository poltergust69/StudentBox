package com.studentbox.api.models.skill;

import com.studentbox.api.entities.skill.Skill;
import com.studentbox.api.entities.student.Student;
import lombok.Data;

@Data
public class SkillCreationModel {

    private Skill skill;
    private Student student;
    private String name;

}
