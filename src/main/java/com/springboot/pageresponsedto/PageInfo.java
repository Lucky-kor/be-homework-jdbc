package com.springboot.pageresponsedto;

import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter
public class PageInfo {
        private int page;
        private int size;
        private int totalElements;
        private int totalPages;
    }

