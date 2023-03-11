package com.studentbox.api.utils.containers;

import org.springframework.data.domain.Sort;

public class ConstantsContainer {
    public static final Integer DEFAULT_PAGE_SIZE = 10;
    public static final Integer DEFAULT_PAGE_INDEX = 0;
    public static final String DEFAULT_PAGE_SIZE_STRING = DEFAULT_PAGE_SIZE.toString();
    public static final String DEFAULT_PAGE_INDEX_STRING = DEFAULT_PAGE_INDEX.toString();
    public static final Sort POSTS_DEFAULT_SORT_BY = Sort.by("modifiedAt").descending();
    public static final Integer REPLIES_DEFAULT_PAGE_SIZE = 5;
    public static final Integer DEFAULT_LIKES_COUNT = 0;
    public static final Long ACCESS_TOKEN_VALID_FOR_MILLISECONDS = 900000L;
    public static final Long REFRESH_TOKEN_VALID_FOR_MILLISECONDS = 604800000L;
}
