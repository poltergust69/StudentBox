package com.studentbox.api.utils.containers;

import org.springframework.data.domain.Sort;

import java.util.Random;
import java.util.regex.Pattern;

import static com.studentbox.api.utils.containers.ExceptionMessageContainer.UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE;

public class ConstantsContainer {
    private ConstantsContainer(){
        throw new IllegalStateException(UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE);
    }
    public static final Integer DEFAULT_PAGE_SIZE = 10;
    public static final Integer DEFAULT_PAGE_INDEX = 0;
    public static final Sort POSTS_DEFAULT_SORT_BY = Sort.by("modifiedAt").descending();
    public static final Integer REPLIES_DEFAULT_PAGE_SIZE = 5;
    public static final Integer DEFAULT_LIKES_COUNT = 0;
    public static final Long ACCESS_TOKEN_VALID_FOR_MILLISECONDS = 900000L;
    public static final Long REFRESH_TOKEN_VALID_FOR_MILLISECONDS = 604800000L;
    public static final Pattern USERNAME_PATTERN = Pattern.compile("^(?=.{5,50}$)(?!.*[_.-]{2})[a-z0-9._-]+$");
    public static final Pattern SPECIAL_CHARACTER_PATTERN = Pattern.compile("^(.*[_.-]{1})+[a-z0-9._-]+$");
    public static final Pattern EMAIL_PATTERN = Pattern.compile("^\\S+@\\S+\\.\\S{2,4}$");
    public static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
    public static final Pattern URL_PATTERN = Pattern.compile("^[(http(s)?)://(www.)?a-zA-Z0-9@:%._+~#=-]{2,512}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_+.~#?&//=]*)$");
    public static final Integer POST_TITLE_MAX_LENGTH = 100;
    public static final String PLACEHOLDER_AVATAR_URL = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/59/User-avatar.svg/2048px-User-avatar.svg.png";
    public static final String ROLE_PREFIX = "ROLE_";
    public static final Integer JOB_POSITION_NAME_MAX_LENGTH = 100;
    public static final Boolean LIMITED_VIEW_ENABLED = true;
    public static final Boolean LIMITED_VIEW_DISABLED = false;
    public static final Integer REPLY_COUNT_LIMITED = 3;
    public static final Random RANDOM_GENERATOR = new Random();
    public static final String ALL_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static final String STUDENT_ROLE = "ROLE_STUDENT";
    public static final String COMPANY_ROLE = "ROLE_COMPANY";
    public static final double EXPERIENCE_MATCHING_JOB_POSITION_POINTS = 20;
    public static final double EXPERIENCE_YEAR_POINTS = 25;
    public static final double EXPERIENCE_MONTH_POINTS = 2;
    public static final double EXPERIENCE_DAYS_POINTS = 0.06;
    public static final double MINIMUM_PERCENTAGE_MATCHING_SKILLS = 65D;
}
