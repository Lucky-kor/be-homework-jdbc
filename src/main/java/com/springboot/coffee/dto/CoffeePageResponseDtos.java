package com.springboot.coffee.dto;

import com.springboot.PageInfo;
import com.springboot.coffee.entity.Coffee;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CoffeePageResponseDtos {
    private List<Coffee>coffees;
    private PageInfo pageInfo;
}
