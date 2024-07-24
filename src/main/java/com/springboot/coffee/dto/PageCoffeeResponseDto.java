package com.springboot.coffee.dto;

import com.springboot.member.entity.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PageCoffeeResponseDto {
    private List<CoffeeResponseDto> data;
    private PageInfo pageInfo;
}
