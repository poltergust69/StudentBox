package com.studentbox.api.web;

import com.studentbox.api.common.PermissionEvaluator;
import com.studentbox.api.models.student.certificate.CertificateCreationModel;
import com.studentbox.api.models.student.certificate.CertificateModel;
import com.studentbox.api.models.student.education.EducationCreationModel;
import com.studentbox.api.models.student.education.EducationInfo;
import com.studentbox.api.models.student.education.EducationModificationModel;
import com.studentbox.api.models.student.employment.EmploymentInfoCreationModel;
import com.studentbox.api.models.student.skill.SkillModel;
import com.studentbox.api.service.student.StudentService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/skills")
    @ApiOperation(value="Get a list of skills of the student.", response = SkillModel[].class)
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public ResponseEntity<List<SkillModel>> getSkills(
    ){
        return ResponseEntity.ok(studentService.getSkillsForStudent());
    }

    @PostMapping("/skills/{skillId}")
    @ApiOperation(value="Add a new skill to the user.")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public ResponseEntity addSkill(
            @PathVariable String skillId
    ){
        studentService.addSkillToStudent(skillId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value="Delete a student skill.")
    @DeleteMapping("/skills/{skillId}")
    @PreAuthorize("hasRole('ROLE_STUDENT') && @permissionEvaluator.hasPermissionToAlterStudentSkill(principal, #skillId)")
    public ResponseEntity deleteStudentSkill(
            @PathVariable String skillId,
            PermissionEvaluator permissionEvaluator
    ){
        studentService.deleteStudentSkill(skillId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/education")
    @ApiOperation(value="Get a list of logged-in user's education info.", response = EducationInfo[].class)
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public ResponseEntity<List<EducationInfo>> getEducationInfo(){
        return ResponseEntity.ok(studentService.getEducationInfo());
    }

    @PostMapping("/education")
    @ApiOperation(value="Add a new item to logged-in user's education.")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public ResponseEntity addEducation(
        EducationCreationModel educationCreationModel
    ){
        studentService.addEducationToStudent(educationCreationModel);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/education/{educationId}")
    @ApiOperation(value="Modify a student education.")
    @PreAuthorize("hasRole('ROLE_STUDENT') && @permissionEvaluator.hasPermissionToAlterStudentEducation(principal, #educationId)")
    public ResponseEntity editStudentEducation(
            @PathVariable String educationId,
            EducationModificationModel educationModificationModel
    ){
        studentService.editStudentEducation(educationId, educationModificationModel);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ApiOperation(value="Delete a student education.")
    @DeleteMapping("/education/{educationId}")
    @PreAuthorize("hasRole('ROLE_STUDENT') && @permissionEvaluator.hasPermissionToAlterStudentEducation(principal, #educationId)")
    public ResponseEntity deleteStudentEducation(
            @PathVariable String educationId
    ){
        studentService.deleteStudentEducation(educationId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/certificates")
    @ApiOperation(value="Get a list of logged-in user's skill certificates.", response = CertificateModel[].class)
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public ResponseEntity<List<CertificateModel>> getSkillCertificates(){
        return ResponseEntity.ok(studentService.getCertificates());
    }

    @PostMapping("/certificates")
    @ApiOperation(value="Add a new certificate to logged-in user skill certificates.")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public ResponseEntity addSkillCertificate(
            CertificateCreationModel certificateCreationModel
    ){
        studentService.addCertificateToStudent(certificateCreationModel);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value="Delete a skill certificate.")
    @DeleteMapping("/certificates/{certificateId}")
    @PreAuthorize("hasRole('ROLE_STUDENT') && @permissionEvaluator.hasPermissionToAlterStudentCertificate(principal, #certificateId)")
    public ResponseEntity deleteSkillCertificate(
            @PathVariable String certificateId,
            PermissionEvaluator permissionEvaluator
    ){
        studentService.deleteCertificate(certificateId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/employment-info")
    @ApiOperation(value="Add a new employment info to logged-in user.")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public ResponseEntity addEmploymentInfo(
            EmploymentInfoCreationModel employmentInfoCreationModel
    ){
        studentService.addEmploymentInfoToStudent(employmentInfoCreationModel);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value="Delete employment info.")
    @DeleteMapping("/employment-info/{employmentId}")
    @PreAuthorize("hasRole('ROLE_STUDENT') && @permissionEvaluator.hasPermissionToAlterEmploymentInfo(principal, #employmentId)")
    public ResponseEntity deleteEmploymentInfo(
            @PathVariable String employmentId,
            PermissionEvaluator permissionEvaluator
    ){
        studentService.deleteEmploymentInfo(employmentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
