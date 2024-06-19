package com.springboot.coffee.dto;

import com.springboot.PageInfo;
import com.springboot.coffee.entity.Coffee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CoffeeResponseDto {
    private List<Coffee> coffees;
    private PageInfo pageInfo;
}
