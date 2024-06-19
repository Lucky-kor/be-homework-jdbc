package com.springboot.coffee.dto;


import com.springboot.member.dto.PageInfoResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CoffeesResponseDto {
    private List<CoffeeResponseDto> coffeeData;
    private PageInfoResponseDto pageInfo;
}
