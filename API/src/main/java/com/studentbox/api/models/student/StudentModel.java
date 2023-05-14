package com.studentbox.api.models.student;

import com.studentbox.api.models.student.certificate.CertificateModel;
import com.studentbox.api.models.student.education.EducationInfoModel;
import com.studentbox.api.models.student.employment.EmploymentInfoModel;
import com.studentbox.api.models.student.skill.SkillModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class StudentModel {
    private UUID id;
    private String username;
    private String avatarUrl;
    private String name;
    private Integer age;
    private List<CertificateModel> certificates;
    private List<EmploymentInfoModel> employments;
    private List<EducationInfoModel> education;
    private List<SkillModel> skills;
}
