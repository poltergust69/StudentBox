package com.studentbox.api.utils.containers;

import com.studentbox.api.common.CustomAuthentication;
import com.studentbox.api.entities.company.job.JobOffer;
import com.studentbox.api.entities.student.Student;
import com.studentbox.api.entities.student.employment.EmploymentInfo;
import com.studentbox.api.entities.student.skill.Skill;
import com.studentbox.api.exception.NotAuthenticatedException;
import org.json.JSONObject;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import static com.studentbox.api.utils.containers.ConstantsContainer.*;
import static com.studentbox.api.utils.containers.ExceptionMessageContainer.UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE;
import static java.util.Objects.isNull;

public class SharedMethodContainer {
    private SharedMethodContainer() {
        throw new IllegalStateException(UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE);
    }

    public static String generateRandomSecretKey(){
        StringBuilder builder = new StringBuilder();

        for(int i=0; i<64; i++){
            builder.append(
                    ALL_CHARACTERS.charAt(
                            RANDOM_GENERATOR.nextInt(0,ALL_CHARACTERS.length())
                    )
            );
        }

        return builder.toString();
    }

    public static boolean isUserAuthenticated(){
        return SecurityContextHolder.getContext().getAuthentication() instanceof CustomAuthentication;
    }

    public static UUID getAuthenticatedUserId(){
        if(isUserAuthenticated()){
            return UUID.fromString(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        }
        else{
            throw new NotAuthenticatedException();
        }
    }

    public static UUID extractUserIdFromToken(String token){
        String [] parts = token.split("\\.");
        String payload = parts[1];
        String decodedPayload = decodeBase64(payload);

        JSONObject dataJson = new JSONObject(decodedPayload);

        String sub = dataJson.getString(DATA_PARAM);

        JSONObject payloadJson = new JSONObject(sub);

        String userId = payloadJson.has(USER_ID_PARAM) ? payloadJson.getString(USER_ID_PARAM) : null;

        if(dataJson.getLong(EXPIRY_PARAM) < (System.currentTimeMillis() / 1000) || isNull(userId)){
            throw new NotAuthenticatedException();
        }

        return isNull(userId) ? null : UUID.fromString(userId);
    }

    private static String decodeBase64(String encodedString) {
        return new String(Base64.getUrlDecoder().decode(encodedString));
    }

    public static double calculateMatchingSkillsPercentage(JobOffer jobOffer, Student student){
        Set<String> studentSkillsPresence = new HashSet<>();
        student.getSkills().forEach(skill -> studentSkillsPresence.add(skill.getId().toString()));

        List<Skill> offerSkills = jobOffer.getSkills();

        double skillsPresentForStudent = offerSkills
                .stream()
                .map(skill -> studentSkillsPresence.contains(skill.getId().toString()))
                .mapToDouble(skill -> 1)
                .sum();

        return skillsPresentForStudent / offerSkills.size();
    }

    public static double calculateJobOfferScore(JobOffer jobOffer, Student student){
        double skillsPresencePercentage = calculateMatchingSkillsPercentage(jobOffer, student);

        double certificatesPoints = student.getCertificates().size() * 0.5;

        double experienceScore =  calculateJobOfferScore(jobOffer, student);

        return skillsPresencePercentage + certificatesPoints + experienceScore;
    }

    public double calculateExperienceScore(JobOffer jobOffer, Student student){
        List<EmploymentInfo> userEmployments = student.getEmployments();

        AtomicReference<Double> score = new AtomicReference<>(0D);

        userEmployments.forEach(employment -> {
            Period period = Period.between(employment.getStartedAt(), isNull(employment.getEndedAt()) ? LocalDate.now() : employment.getEndedAt());

            score.updateAndGet(v ->
                (
                        v +
                        period.getYears() * EXPERIENCE_YEAR_POINTS +
                        period.getMonths() * EXPERIENCE_MONTH_POINTS +
                        period.getDays() * EXPERIENCE_DAYS_POINTS
                )
            );
        });

        boolean hasExperienceInJobOfferPosition = userEmployments
                .stream()
                .anyMatch(employment -> employment.getJobPosition().getId().equals(jobOffer.getJobPosition().getId()));

        if(hasExperienceInJobOfferPosition){
            score.updateAndGet(v -> (v + EXPERIENCE_MATCHING_JOB_POSITION_POINTS));
        }

        return score.get();
    }
}
