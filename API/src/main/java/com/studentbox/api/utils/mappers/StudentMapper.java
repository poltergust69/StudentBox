package com.studentbox.api.utils.mappers;

import com.studentbox.api.entities.student.Student;
import com.studentbox.api.models.student.StudentDetailsModel;
import com.studentbox.api.models.student.StudentModel;
import com.studentbox.api.models.user.UserDetailsModel;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import static com.studentbox.api.utils.containers.ExceptionMessageContainer.UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE;

public class StudentMapper {
    private StudentMapper() {
        throw new IllegalStateException(UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE);
    }

    public static StudentModel mapToModel(Student student){
        StudentModel studentModel = new StudentModel();

        studentModel.setId(student.getId());
        studentModel.setUsername(student.getUser().getUsername());
        studentModel.setName(student.getFullName());
        studentModel.setAvatarUrl(student.getUser().getAvatarUrl());

        int age = Period.between(student.getDateOfBirth(), LocalDate.now()).getYears();
        studentModel.setAge(age);

        studentModel.setCertificates(CertificateMapper.mapAllToModel(student.getCertificates()));
        studentModel.setEmployments(EmploymentInfoMapper.mapAllToModel(student.getEmployments()));
        studentModel.setEducation(EducationInfoMapper.mapAllToModel(student.getEducations()));
        studentModel.setSkills(SkillMapper.mapAllToModel(student.getSkills()));

        return studentModel;
    }

    public static List<StudentModel> mapAllToModel(List<Student> studentList){
        return studentList
                .stream()
                .map(StudentMapper::mapToModel)
                .toList();
    }

    public static StudentDetailsModel mapToDetailsModel(Student student){
        UserDetailsModel userDetailsModel = UserMapper.mapToDetailsModel(student.getUser());
        StudentDetailsModel studentDetailsModel = new StudentDetailsModel(userDetailsModel);

        studentDetailsModel.setFirstName(student.getFirstName());
        studentDetailsModel.setLastName(student.getLastName());
        studentDetailsModel.setDateOfBirth(student.getDateOfBirth().toString());

        return studentDetailsModel;
    }
}
