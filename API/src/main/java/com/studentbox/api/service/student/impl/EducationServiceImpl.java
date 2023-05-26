package com.studentbox.api.service.student.impl;

import com.studentbox.api.entities.student.education.Education;
import com.studentbox.api.entities.student.education.StudentEducation;
import com.studentbox.api.entities.student.Student;
import com.studentbox.api.exception.NotFoundException;
import com.studentbox.api.models.student.education.EducationCreationModel;
import com.studentbox.api.models.student.education.EducationInfoModel;
import com.studentbox.api.repository.student.EducationRepository;
import com.studentbox.api.repository.student.StudentEducationRepository;
import com.studentbox.api.service.student.EducationService;
import com.studentbox.api.utils.mappers.EducationInfoMapper;
import com.studentbox.api.utils.validators.StudentEducationValidator;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.studentbox.api.utils.containers.ExceptionMessageContainer.STUDENT_EDUCATION_NOT_FOUND_EXCEPTION_MESSAGE;

@Service
@AllArgsConstructor
public class EducationServiceImpl implements EducationService {
    private final StudentEducationRepository studentEducationRepository;
    private final EducationRepository educationRepository;

    private final Logger logger = LoggerFactory.getLogger(EducationServiceImpl.class);

    @Override
    public StudentEducation findById(String id) {
        return studentEducationRepository.findById(UUID.fromString(id)).orElseThrow(() -> new NotFoundException(String.format(STUDENT_EDUCATION_NOT_FOUND_EXCEPTION_MESSAGE, id)));
    }

    @Override
    public List<EducationInfoModel> getEducationInfo(Student student) {

        List<StudentEducation> studentEducations = studentEducationRepository.findAllByStudent(student);

        return EducationInfoMapper.mapAllToModel(studentEducations);
    }

    @Override
    public void addEducationToStudent(Student student, EducationCreationModel educationCreationModel) {
        Education education = educationRepository.findByName(educationCreationModel.getSchoolName());

        StudentEducation studentEducation = new StudentEducation(educationCreationModel, student, education);

        try{
            studentEducationRepository.save(studentEducation);
        }
        catch (Exception e){
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public void editStudentEducation(String id, EducationCreationModel educationCreationModel) {
        StudentEducation studentEducation = findById(id);

        StudentEducationValidator.validateStudentEducation(educationCreationModel);

        studentEducation.modifyStudentEducation(educationCreationModel);

        studentEducationRepository.save(studentEducation);
    }

    @Override
    public void deleteStudentEducation(String id) {
        StudentEducation studentEducation = findById(id);

        studentEducationRepository.delete(studentEducation);
    }

}
