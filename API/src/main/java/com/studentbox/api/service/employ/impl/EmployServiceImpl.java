package com.studentbox.api.service.employ.impl;

import com.studentbox.api.entities.company.Company;
import com.studentbox.api.entities.company.job.JobOffer;
import com.studentbox.api.entities.employ.JobSkill;
import com.studentbox.api.entities.employ.StudentJobOffer;
import com.studentbox.api.entities.student.Student;
import com.studentbox.api.entities.student.skill.Skill;
import com.studentbox.api.entities.student.skill.StudentSkill;
import com.studentbox.api.entities.user.User;
import com.studentbox.api.exception.NotFoundException;
import com.studentbox.api.models.employ.SortedStudentJobOffer;
import com.studentbox.api.repository.company.CompanyRepository;
import com.studentbox.api.repository.company.JobOfferRepository;
import com.studentbox.api.repository.employ.JobSkillRepository;
import com.studentbox.api.repository.employ.StudentJobOfferRepository;
import com.studentbox.api.repository.student.StudentRepository;
import com.studentbox.api.repository.student.StudentSkillRepository;
import com.studentbox.api.service.employ.EmployService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.studentbox.api.utils.containers.ExceptionMessageContainer.*;

@AllArgsConstructor
@Service
public class EmployServiceImpl implements EmployService {

    private final StudentSkillRepository skillRepository;
    private final StudentJobOfferRepository studentJobOfferRepository;
    private final JobSkillRepository jobSkillRepository;
    private final StudentRepository studentRepository;
    private final JobOfferRepository jobOfferRepository;
    private final CompanyRepository companyRepository;



    @Override
    public Collection<SortedStudentJobOffer> offers(UUID studentId) {


        Student student = this.findStudentById(studentId);
        List<Skill> skills = this.findAllSkillsForStudent(student);
        List<JobOffer> jobOffers = this.studentJobOfferRepository.findAllNotCheckedJobsForStudent();

        Collection<SortedStudentJobOffer> collection =
                this.filterStudents(student, skills, jobOffers);

        collection.forEach(model -> this.studentJobOfferRepository.save(new StudentJobOffer(model.getStudent(),model.getJobOffer(), model.getScore())));

        return collection;

    }

    @Override
    public Collection<SortedStudentJobOffer> students(UUID companyId) {

        List<JobOffer> offers = this.studentJobOfferRepository.findAllNotCheckedAllJobsForCompany(companyId);
        List<Student> students = this.studentRepository.findAll();

        Collection<SortedStudentJobOffer> collection =  students
                .stream()
                .map(st -> filterStudents(st, this.findAllSkillsForStudent(st), offers))
                .flatMap(Collection::stream)
                .collect(Collectors.toCollection(TreeSet::new));

        collection.forEach(model -> this.studentJobOfferRepository.
                save(new StudentJobOffer(model.getStudent(),model.getJobOffer(), model.getScore())));

        return collection;

    }

    @Override
    public boolean swipeRight(User user, UUID studentId, UUID jobOfferId) {

        this.findObjects(user,studentId,jobOfferId,'A');
        return true;
    }

    @Override
    public boolean swipeLeft(User user,UUID studentId, UUID jobOfferId) {
        this.findObjects(user,studentId,jobOfferId,'D');
        return true;

    }

    @Override
    public Collection<StudentJobOffer> findAllMatched(UUID userId) {
        Optional<Student> loggedStudent = this.studentRepository.findById(userId);

        if (loggedStudent.isPresent()){

            return this.studentJobOfferRepository.findAllMatchedJobsForStudent(userId);
        }
        Company company = this.companyRepository.findById(userId).
        orElseThrow(() -> new NotFoundException(COMPANY_NOT_FOUND_EXCEPTION_MESSAGE));

        return this.studentJobOfferRepository.findAllMatchedJobsForCompany(userId);

    }

    private void findObjects(User user,UUID studentId, UUID jobOfferId, Character type){
        Optional<Student> loggedStudent = this.studentRepository.findById(user.getId());

        Student student = this.findStudentById(studentId);
        JobOffer jobOffer = this.jobOfferRepository.findById(jobOfferId)
                .orElseThrow(() -> new NotFoundException(JOB_OFFER_NOT_FOUND_EXCEPTION_MESSAGE));
        makeSwiping(loggedStudent,student,jobOffer,type);
    }

    private void makeSwiping(Optional<Student> loggedStudent, Student student, JobOffer jobOffer,Character type){


        StudentJobOffer studentJobOffer = this.studentJobOfferRepository.findByJobOfferAndStudent(jobOffer, student);
//        studentJobOffer = studentJobOffer != null ? studentJobOffer : new StudentJobOffer(student,jobOffer);

            if (loggedStudent.isPresent()){
                studentJobOffer.setStudentStatus(type);
            }
            else{
                studentJobOffer.setCompanyStatus(type);
            }

            studentJobOfferRepository.save(studentJobOffer);

    }

    private Collection<SortedStudentJobOffer> filterStudents(Student student, List<Skill> skills, List<JobOffer> offers) {

        return offers
                .stream()
                .map(job -> new SortedStudentJobOffer(filterJobOffer(skills, job) +
                        calculatePointsExperience(student, job), job, student))
                .filter(offer -> offer.getScore() >= 0.5)
                .collect(Collectors.toCollection(TreeSet::new));

    }


    private double filterJobOffer(List<Skill> skills, JobOffer jobOffer) {
        List<JobSkill> jobSkills = this.jobSkillRepository.findAllByJobOffer(jobOffer);

        long matchedSkills = jobSkills
                .stream()
                .map(JobSkill::getSkill)
                .filter(jobSkill -> checkSkillOfStudentForJob(skills, jobSkill))
                .count();

        double totalSkills = jobSkills.size();

        return (matchedSkills / totalSkills);

    }

    private boolean checkSkillOfStudentForJob(List<Skill> skills, Skill jobSkill) {

        return skills.contains(jobSkill);
    }

    private List<Skill> findAllSkillsForStudent(Student student) {
        return this.skillRepository
                .findAllByStudent(student)
                .stream()
                .map(StudentSkill::getSkill)
                .collect(Collectors.toList());
    }

    private double calculatePointsExperience(Student student, JobOffer jobOffer) {
        return
                student
                        .getEmployments()
                        .stream()
                        .mapToDouble(emp -> emp.calculateExperience() + emp.givePointsIfSamePosition(jobOffer.getJobPosition()))
                        .sum();

    }

    private Student findStudentById(UUID id) {
        return this
                .studentRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException
                        (String.format(STUDENT_NOT_FOUND_EXCEPTION_MESSAGE, id)));
    }
}
