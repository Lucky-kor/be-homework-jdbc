package com.springboot;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PageInfo {
    private int page;
    private int size;
    private int totalElements;

    public PageInfo(int page, int size, int totalElements, int totalPages) {
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    private int totalPages;
}
