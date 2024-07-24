package com.springboot.member.dto;

import com.springboot.member.entity.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PageResponseDto {
    private List<MemberResponseDto> data;
    private PageInfo pageInfo;



}
