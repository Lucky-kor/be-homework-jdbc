package com.springboot.member.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MemberPageResponseDto {
    private List<MemberResponseDto> data;
    private PageInfo pageInfo;

    @Getter
    @Builder
    public static class PageInfo{
        private int page;
        private int size;
        private long totalElements;
        private long totalPages;
    }
}
