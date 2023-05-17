package com.studentbox.api.models.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageModel<T>{
    private List<T> payload;
    private int totalPageCount;
    private int pageSize;
    private int pageIndex;

    public PageModel(List<T> page, Pageable pageable, int totalPageCount) {
        this.payload = page;
        this.pageIndex = pageable.getPageNumber();
        this.pageSize = pageable.getPageSize();
        this.totalPageCount = totalPageCount;
    }
}

