package com.springboot.order.dto;

import com.springboot.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class OrderResponseDto2 {
    private List<OrderResponseDto> orderResponseDtoList;
    private PageInfo pageInfo;
}
