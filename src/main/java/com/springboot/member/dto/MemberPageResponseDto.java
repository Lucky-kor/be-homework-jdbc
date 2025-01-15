package com.springboot.member.dto;

import com.springboot.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

//
@Getter
@Builder
public class MemberPageResponseDto {

    //1page 정보 : List ->
    private List<MemberResponseDto> data;
    private PageInfo pageInfo;

    @Getter
    @Builder
    public static class PageInfo{
        private int page;
        private int size;
        private int totalElements;
        private int totalPages;



    }
}
