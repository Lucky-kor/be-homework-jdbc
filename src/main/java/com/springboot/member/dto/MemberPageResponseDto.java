package com.springboot.member.dto;

import com.springboot.member.entity.Member;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class MemberPageResponseDto {
    private List<MemberResponseDto> date;
    private PageInfo pageInfo;

    public MemberPageResponseDto(List<MemberResponseDto> date, Page page) {
        this.date = date;
        this.pageInfo = new PageInfo(page.getNumber()+1, page.getSize(),page.getTotalElements(),page.getTotalPages());
    }
}
