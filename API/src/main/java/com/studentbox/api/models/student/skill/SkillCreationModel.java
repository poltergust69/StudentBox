package com.studentbox.api.models.student.skill;

import com.studentbox.api.entities.student.skill.Skill;
import com.studentbox.api.entities.student.Student;
import lombok.Data;

@Data
public class SkillCreationModel {

    private Skill skill;
    private Student student;
    private String name;

}
