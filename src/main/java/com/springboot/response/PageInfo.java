package com.springboot.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
    @Getter
    @AllArgsConstructor
    public class PageInfo {
        @DecimalMin(value = "1")
        private int page;
        @DecimalMin(value = "1")
        private int size;
        private int totalElements;
        private int totalPages;
}
