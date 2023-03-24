package com.studentbox.api.utils.containers;

import org.springframework.data.domain.Sort;

import java.util.regex.Pattern;

public class ConstantsContainer {
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
}
