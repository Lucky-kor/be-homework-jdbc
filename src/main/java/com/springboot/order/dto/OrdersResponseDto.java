package com.springboot.order.dto;

import com.springboot.member.dto.PageInfoResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class OrdersResponseDto {
    List<OrderResponseDto> orderData;
    PageInfoResponseDto pageInfo;
}
