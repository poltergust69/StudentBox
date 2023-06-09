package com.studentbox.api.entities.student;

import com.studentbox.api.entities.student.certificate.Certificate;
import com.studentbox.api.entities.student.education.StudentEducation;
import com.studentbox.api.entities.student.employment.EmploymentInfo;
import com.studentbox.api.entities.student.skill.Skill;
import com.studentbox.api.entities.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name="students")
@NoArgsConstructor
public class Student{
    @Id
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    @Column(length = 1000)
    private String description;

    @OneToMany
    List<Certificate> certificates;

    @OneToMany
    List<EmploymentInfo> employments;

    @OneToMany
    List<StudentEducation> educations;

    @ManyToMany
    @JoinTable(name="student_skills",
            joinColumns = @JoinColumn(name="student_id"),
            inverseJoinColumns = @JoinColumn(name="skill_id"))
    List<Skill> skills;

    public String getFullName(){
        return String.format("%s %s", firstName, lastName);
    }
}
