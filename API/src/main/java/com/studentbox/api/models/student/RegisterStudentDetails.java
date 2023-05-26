package com.studentbox.api.models.student;

import com.studentbox.api.models.user.RegisterUserDetails;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterStudentDetails extends RegisterUserDetails {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String description;
}
