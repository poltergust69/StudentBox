package com.studentbox.api.entities.student.skill;

import com.studentbox.api.entities.student.Student;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name="student_skills")
@NoArgsConstructor
public class StudentSkill {

    @Id
    private UUID id;

    @OneToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public StudentSkill(Skill skill, Student student){
        this.id = UUID.randomUUID();
        this.skill = skill;
        this.student = student;
    }
}
