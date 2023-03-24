package com.studentbox.api.entities.student;

import com.studentbox.api.entities.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name="students")
@NoArgsConstructor
public class Student {

    @Id
    @OneToOne
    @JoinColumn(name = "id")
    private User user;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    @Column(length = 1000)
    private String description;
}
