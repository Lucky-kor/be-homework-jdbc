package com.springboot.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MemberPageResponseDto {
    private List<MemberResponseDto> data; // 멤버 정보가 들어가는 리스트
    private PageInfo pageInfo;  // 페이지 정보를 담는 태그

    @Getter
    @Builder
    public static class PageInfo {
        private int page;
        private int size;
        private int totalElements;
        private int totalPages;
    }
}
