package com.studentbox.api.models.common;

import lombok.Data;

import java.util.Objects;

import static com.studentbox.api.utils.containers.ConstantsContainer.DEFAULT_PAGE_INDEX;
import static com.studentbox.api.utils.containers.ConstantsContainer.DEFAULT_PAGE_SIZE;

@Data
public class    PaginationModel {
    private Integer pageIndex;
    private Integer pageSize;

    public PaginationModel() {
        pageIndex = DEFAULT_PAGE_INDEX;
        pageSize = DEFAULT_PAGE_SIZE;
    }

    public PaginationModel(Integer pageIndex, Integer pageSize) {
        this.pageIndex = Objects.isNull(pageIndex) ? DEFAULT_PAGE_INDEX : pageIndex;
        this.pageSize = Objects.isNull(pageSize) ? DEFAULT_PAGE_SIZE : pageSize;
    }
}
