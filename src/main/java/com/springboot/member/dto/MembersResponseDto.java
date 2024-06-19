package com.springboot.member.dto;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class MembersResponseDto<T> {
    private List<T> data;
    private PageInfoResponseDto pageInfo;

    public MembersResponseDto(List<T> data, Page page) {
        this.data = data;
        this.pageInfo = new PageInfoResponseDto(page.getNumber() + 1,
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages());
    }
}
