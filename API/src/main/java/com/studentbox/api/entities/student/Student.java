package com.studentbox.api.entities.student;

import com.studentbox.api.entities.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name="students")
@NoArgsConstructor
public class Student {
    @Id
    private UUID id;

    @OneToOne
    @JoinColumn(name = "id")
    private User user;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    @Column(length = 1000)
    private String description;

    public String getFullName(){
        return String.format("%s %s", firstName, lastName);
    }
}
