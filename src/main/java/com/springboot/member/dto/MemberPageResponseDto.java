package com.springboot.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MemberPageResponseDto {

    private List<MemberResponseDto> data;
    private MyPage myPage;

    @Getter
    @Builder
    public static class MyPage {
        private int page;
        private int size;
        private int totalElements;
        private int totalPages;
    }
}
