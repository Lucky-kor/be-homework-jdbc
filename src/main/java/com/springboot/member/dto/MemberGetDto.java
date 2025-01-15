package com.springboot.member.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
public class MemberGetDto {
    @Min(1)
    private int page;
    @Min(1)
    private int size;
}