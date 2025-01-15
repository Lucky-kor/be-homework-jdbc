package com.springboot.member.dto;

import com.springboot.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PageResponseDto {
    private List<MemberResponseDto> data; // 페이지의 데이터 리스트
    private PageInfo pageInfo; // 페이지 정보

    @Getter
    @Builder
    public static class PageInfo {
        private int page; // 현재 페이지 번호
        private int size; // 페이지 크기
        private long totalElements; // 전체 데이터 개수
        private int totalPages; // 전체 페이지 개수
    }
}