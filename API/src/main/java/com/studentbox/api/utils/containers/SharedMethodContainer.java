package com.studentbox.api.utils.containers;

import com.studentbox.api.common.CustomAuthentication;
import com.studentbox.api.entities.company.job.JobOffer;
import com.studentbox.api.entities.student.Student;
import com.studentbox.api.entities.student.employment.EmploymentInfo;
import com.studentbox.api.entities.student.skill.Skill;
import com.studentbox.api.exception.NotAuthenticatedException;
import com.studentbox.api.exception.NotValidException;
import org.json.JSONObject;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;

import static com.studentbox.api.utils.containers.ConstantsContainer.*;
import static com.studentbox.api.utils.containers.ExceptionMessageContainer.NOT_VALID_PASSWORD_EXCEPTION_MESSAGE;
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

    public static boolean isNameInvalid(String name) {
        return isNull(name) || name.isBlank() || name.trim().length() == 0;
    }

    public static boolean isDescriptionInvalid(String description){
        return !isNull(description) && description.length() > 1000;
    }

    public static boolean isDateOfBirthInvalid(String dateOfBirth){
        return isNull(dateOfBirth) || dateOfBirth.trim().length() != 10 || LocalDate.parse(dateOfBirth).isAfter(LocalDate.now().minusYears(MINIMUM_AGE_OF_USER));
    }
    public static void validateUserPassword(String password){
        if(isPasswordInvalid(password)){
            throw new NotValidException(NOT_VALID_PASSWORD_EXCEPTION_MESSAGE);
        }
    }

    public static boolean isUsernameSymbolsInvalid(String username) {
        if (isNull(username))
            return true;
        Matcher specialCharacters = SPECIAL_CHARACTER_PATTERN.matcher(username);
        Matcher usernameCharacters = USERNAME_PATTERN.matcher(username);

        if(username.contains("_") || username.contains("-") || username.contains("."))
            return !usernameCharacters.matches() || !specialCharacters.matches();
        else
            return !usernameCharacters.matches();
    }

    public static boolean isEmailInvalid(String email){
        Matcher emailMatcher = EMAIL_PATTERN.matcher(email);
        return !emailMatcher.matches();
    }

    public static boolean isPasswordInvalid(String password){
        Matcher passwordMatcher = PASSWORD_PATTERN.matcher(password);
        return !passwordMatcher.matches();
    }

    public static boolean isAvatarUrlInvalid(String avatarUrl){
        Matcher avatarMatcher = URL_PATTERN.matcher(avatarUrl);
        return !avatarMatcher.matches();
    }
}
