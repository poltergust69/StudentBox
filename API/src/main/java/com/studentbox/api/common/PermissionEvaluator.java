package com.studentbox.api.common;

import com.studentbox.api.entities.student.employment.EmploymentInfo;
import com.studentbox.api.exception.NotFoundException;
import com.studentbox.api.repository.company.JobOfferRepository;
import com.studentbox.api.repository.forum.PostReplyRepository;
import com.studentbox.api.repository.forum.PostRepository;
import com.studentbox.api.repository.student.CertificateRepository;
import com.studentbox.api.repository.student.EmploymentInfoRepository;
import com.studentbox.api.repository.student.StudentEducationRepository;
import com.studentbox.api.repository.student.StudentSkillRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.studentbox.api.utils.containers.ExceptionMessageContainer.*;

@Component
@AllArgsConstructor
public class PermissionEvaluator {
    private PostRepository postRepository;
    private PostReplyRepository postReplyRepository;
    private JobOfferRepository jobOfferRepository;
    private StudentEducationRepository studentEducationRepository;
    private CertificateRepository certificateRepository;
    private EmploymentInfoRepository employmentInfoRepository;
    private StudentSkillRepository studentSkillRepository;

    public boolean hasPermissionToAlterPost(String principal, String postId){
        UUID id = UUID.fromString(postId);
        var post = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(POST_NOT_FOUND_EXCEPTION_MESSAGE, postId)));

        return post.getAuthor().getUsername().equals(principal);
    }

    public boolean hasPermissionToAlterPostReply(String principal, String replyId){
        UUID id = UUID.fromString(replyId);
        var reply = postReplyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(POST_REPLY_NOT_FOUND_EXCEPTION_MESSAGE, replyId)));

        return reply.getAuthor().getUsername().equals(principal);
    }

    public boolean hasPermissionToAlterJobOffer(String principal, String offerId){
        UUID id = UUID.fromString(offerId);

        var jobOffer = jobOfferRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(POST_REPLY_NOT_FOUND_EXCEPTION_MESSAGE, offerId)));

        return jobOffer.getCompany().getUser().getUsername().equals(principal);
    }

    public boolean hasPermissionToAlterStudentEducation(String principal, String educationId){
        UUID id = UUID.fromString(educationId);

        var studentEducation = studentEducationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(STUDENT_EDUCATION_NOT_FOUND_EXCEPTION_MESSAGE, educationId)));

        return studentEducation.getStudent().getUser().getUsername().equals(principal);
    }

    public boolean hasPermissionToAlterStudentCertificate(String principal, String certificateId){
        UUID id = UUID.fromString(certificateId);

        var  certificate = certificateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(SKILL_CERTIFICATE_NOT_FOUND_EXCEPTION_MESSAGE, certificateId)));

        return certificate.getStudent().getUser().getUsername().equals(principal);
    }

    public boolean hasPermissionToAlterEmploymentInfo(String principal, String employmentId){
        UUID id = UUID.fromString(employmentId);

        EmploymentInfo employment = employmentInfoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(EMPLOYMENT_INFO_NOT_FOUND_EXCEPTION_MESSAGE, employmentId)));

        return employment.getStudent().getUser().getUsername().equals(principal);
    }

    public boolean hasPermissionToAlterStudentSkill(String principal, String skillId){
        UUID id = UUID.fromString(skillId);

        var studentSkill = studentSkillRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(SKILL_NOT_FOUND_EXCEPTION_MESSAGE, skillId)));

        return studentSkill.getStudent().getUser().getUsername().equals(principal);
    }
}
