package com.studentbox.api.models.student;

import com.studentbox.api.entities.student.Student;
import com.studentbox.api.models.user.UserDetailsModel;
import lombok.Data;

@Data
public class StudentDetailsModel extends UserDetailsModel{
    private String firstName;
    private String lastName;
    private String dateOfBirth;

    public StudentDetailsModel(Student student) {
        super(student.getUser());
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.dateOfBirth = student.getDateOfBirth().toString();
    }
}
