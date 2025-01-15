package com.springboot.member.dto;
import com.springboot.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class MembersResponseDto {
    private List<MemberResponseDto> data;
    private PageInfo pageInfo;

    @Getter
    @Builder
    @AllArgsConstructor
    public static class PageInfo {
        private int page;
        private int size;
        private long totalElements;
        private int totalPages;
        private boolean isFirstPage;
        private boolean isLastPage;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MemberResponseDto {
        private long memberId;
        private String name;
        private String email;
        private String phone;
        // 필요한 필드만 포함
    }

    // Page 객체 기반 DTO 생성
    public static MembersResponseDto from(Page<Member> memberPage) {
        List<MemberResponseDto> memberDtos = memberPage.getContent().stream()
                .map(member -> MemberResponseDto.builder()
                        .memberId(member.getMemberId())
                        .name(member.getName())
                        .email(member.getEmail())
                        .phone(member.getPhone())
                        .build())
                .collect(Collectors.toList());

        PageInfo pageInfo = PageInfo.builder()
                .page(memberPage.getNumber())
                .size(memberPage.getSize())
                .totalElements(memberPage.getTotalElements())
                .totalPages(memberPage.getTotalPages())
                .isFirstPage(memberPage.isFirst())
                .isLastPage(memberPage.isLast())
                .build();

        return new MembersResponseDto(memberDtos, pageInfo);
    }
}
