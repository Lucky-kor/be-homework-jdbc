package com.springboot.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PageResponseDto {
    private List<MemberResponseDto> memberData;
    private PageInfo pageInfo;

    @Getter
    @Builder
    public static class PageInfo {
        private int page;
        private int size;
        private int totalElements;
        private int totalPages;
    }
}
