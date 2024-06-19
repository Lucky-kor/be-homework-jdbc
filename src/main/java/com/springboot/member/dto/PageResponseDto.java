package com.springboot.member.dto;
import com.springboot.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;
@Getter
public class PageResponseDto {
    private List<MemberResponseDto> members;

    private PageInfo pageInfo;

    private PageResponseDto(List<MemberResponseDto> members, Page page) {
        this.members = members;
        this.pageInfo =
                new PageInfo(
                        page.getNumber()+1,
                        page.getSize(),
                        (int) page.getTotalElements(),
                        page.getTotalPages()
                );
    }
    public static PageResponseDto of(List<MemberResponseDto> members, Page page) {
        return new PageResponseDto(members, page);
    }
    @AllArgsConstructor
    @Getter
    private static class PageInfo {
        private int page;
        private int size;
        private int totalElements;
        private int totalPages;
    }
}
