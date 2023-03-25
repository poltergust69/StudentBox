package com.studentbox.api.web;

import com.studentbox.api.models.certificate.CertificateCreationModel;
import com.studentbox.api.models.certificate.CertificateModel;
import com.studentbox.api.models.education.EducationCreationModel;
import com.studentbox.api.models.education.EducationInfo;
import com.studentbox.api.models.education.EducationModificationModel;
import com.studentbox.api.models.employment.EmploymentInfoCreationModel;
import com.studentbox.api.models.skill.SkillModel;
import com.studentbox.api.service.StudentService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
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
        try{
            studentService.addSkillToStudent(skillId);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value="Delete a student skill.")
    @DeleteMapping("/skills/{skillId}")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public ResponseEntity deleteStudentSkill(
            @PathVariable String skillId
    ){
        studentService.deleteStudentSkill(skillId);
        return ResponseEntity.ok().build();
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
        try{
            studentService.addEducationToStudent(educationCreationModel);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/education/{educationId}")
    @ApiOperation(value="Modify a student education.")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public ResponseEntity editStudentEducation(
            @PathVariable String educationId,
            EducationModificationModel educationModificationModel
    ){
        studentService.editStudentEducation(educationId, educationModificationModel);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value="Delete a student education.")
    @DeleteMapping("/education/{educationId}")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public ResponseEntity deleteStudentEducation(
            @PathVariable String educationId
    ){
        studentService.deleteStudentEducation(educationId);
        return ResponseEntity.ok().build();
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
        try{
            studentService.addCertificateToStudent(certificateCreationModel);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value="Delete a skill certificate.")
    @DeleteMapping("/certificates/{certificateId}")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public ResponseEntity deleteSkillCertificate(
            @PathVariable String certificateId
    ){
        studentService.deleteCertificate(certificateId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/employment-info")
    @ApiOperation(value="Add a new employment info to logged-in user.")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public ResponseEntity addEmploymentInfo(
            EmploymentInfoCreationModel employmentInfoCreationModel
    ){
        try{
            studentService.addEmploymentInfoToStudent(employmentInfoCreationModel);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value="Delete employment info.")
    @DeleteMapping("/employment-info/{certificateId}")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public ResponseEntity deleteEmploymentInfo(
            @PathVariable String certificateId
    ){
        studentService.deleteEmploymentInfo(certificateId);
        return ResponseEntity.ok().build();
    }

}
