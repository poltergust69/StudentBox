package com.studentbox.api.utils.containers;

public class ExceptionMessageContainer {
    private ExceptionMessageContainer(){
        throw new IllegalStateException(UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE);
    }
    public static final String UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE = "The utility class was initialized when that is not allowed.";
    public static final String USER_NOT_FOUND_EXCEPTION_MESSAGE = "The User with ID \"%s\" does not exist.";
    public static final String USER_NOT_FOUND_EMAIL_EXCEPTION_MESSAGE = "The User with email \"%s\" does not exist.";
    public static final String USER_NOT_AUTHENTICATED_EXCEPTION_MESSAGE = "User failed to be authenticated.";
    public static final String ROLE_WITH_NAME_NOT_FOUND_EXCEPTION_MESSAGE = "The role with name \"%s\" does not exist.";
    public static final String NOT_VALID_USERNAME_EXCEPTION_MESSAGE = "The username \"%s\" is not valid. Please make sure that your username has between 6 and 50 characters, and keep in mind that only numbers, lowercase letters  and the symbols '_', '-' and '.' are allowed.";
    public static final String NOT_VALID_EMAIL_EXCEPTION_MESSAGE = "The E-Mail address \"%s\" is not valid.";
    public static final String NOT_VALID_PASSWORD_EXCEPTION_MESSAGE =  "The password that you provided is not valid.";
    public static final String NOT_VALID_AVATAR_URL_EXCEPTION_MESSAGE = "The avatar URL that you provided is not valid.";
    public static final String NOT_VALID_POST_TITLE_EXCEPTION_MESSAGE = "The title that you provided is not valid.";
    public static final String NOT_VALID_POST_CONTENT_EXCEPTION_MESSAGE = "The content of the post that you provided is not valid.";
    public static final String NOT_VALID_REPLY_CONTENT_EXCEPTION_MESSAGE = "The content of the reply that you provided is not valid.";
    public static final String POST_NOT_FOUND_EXCEPTION_MESSAGE = "The post with ID \"%s\" does not exist.";
    public static final String POST_REPLY_NOT_FOUND_EXCEPTION_MESSAGE = "The reply with ID \"%s\" does not exist.";
    public static final String JOB_POSITION_NOT_FOUND_EXCEPTION_MESSAGE = "The job position with ID \"%s\" does not exist.";
    public static final String NOT_VALID_JOB_POSITION_NAME_EXCEPTION_MESSAGE = "The name that you provided is not valid.";
    public static final String NOT_VALID_JOB_OFFER_DESCRIPTION_EXCEPTION_MESSAGE = "The description of the job offer that you provided is not valid.";
    public static final String NOT_VALID_JOB_OFFER_SALARY_EXCEPTION_MESSAGE = "The salary for the job offer that you provided is not valid.";
    public static final String JOB_OFFER_NOT_FOUND_EXCEPTION_MESSAGE = "The job offer with ID \"%s\" does not exist.";
    public static final String NOT_VALID_COMPANY_NAME_EXCEPTION_MESSAGE = "The company name that you provided is not valid.";
    public static final String SKILL_NOT_FOUND_EXCEPTION_MESSAGE = "The skill with ID \"%s\" does not exist.";
    public static final String STUDENT_HAS_NO_SKILL_EXCEPTION_MESSAGE = "The student \"%s\" does not have skill \"%s\".";
    public static final String STUDENT_EDUCATION_NOT_FOUND_EXCEPTION_MESSAGE = "The student education with ID \"%s\" does not exist.";
    public static final String NOT_VALID_SCHOOL_NAME_EXCEPTION_MESSAGE = "The school name that you provided is not valid.";
    public static final String NOT_VALID_EDUCATION_DESCRIPTION_EXCEPTION_MESSAGE = "The education description that you provided is not valid.";
    public static final String NOT_VALID_DATES_EXCEPTION_MESSAGE = "The dates that you provided are not valid.";
    public static final String SKILL_CERTIFICATE_NOT_FOUND_EXCEPTION_MESSAGE = "The skill certificate with ID \"%s\" does not exist.";
    public static final String EMPLOYMENT_INFO_NOT_FOUND_EXCEPTION_MESSAGE = "The employment info with ID \"%s\" does not exist.";
    public static final String SECRET_KEY_RESET_NOT_VALID_EXCEPTION_MESSAGE = "The password could not be reset because the information provided was not valid, the link has expired.";
    public static final String STUDENT_NOT_FOUND_EXCEPTION_MESSAGE = "The student with username \"%s\" does not exist.";
    public static final String COMPANY_NOT_FOUND_EXCEPTION_MESSAGE = "The company with username \"%s\" does not exist.";
    public static final String PARAMETER_REQUIRED_EXCEPTION_MESSAGE = "The parameter \"%s\" is required!";
}
