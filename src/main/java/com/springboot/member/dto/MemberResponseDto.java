package com.springboot.member.dto;

import com.springboot.PageInfo;
import com.springboot.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

// TODO 변경: Builder 패턴 적용
@Builder
@Getter
@AllArgsConstructor
public class MemberResponseDto {
    private List<Member> data;
    private PageInfo pageInfo;
}
