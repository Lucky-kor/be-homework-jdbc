package com.springboot.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PageInfoResponseDto {
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
}
