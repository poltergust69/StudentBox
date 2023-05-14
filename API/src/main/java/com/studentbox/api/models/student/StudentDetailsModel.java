package com.studentbox.api.models.student;

import com.studentbox.api.models.user.UserDetailsModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentDetailsModel extends UserDetailsModel{
    private String firstName;
    private String lastName;
    private String dateOfBirth;

    public StudentDetailsModel(UserDetailsModel userDetailsModel){
        super(userDetailsModel);
    }
}
