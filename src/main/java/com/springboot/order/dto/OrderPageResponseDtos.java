package com.springboot.order.dto;

import com.springboot.PageInfo;
import lombok.AllArgsConstructor;
import com.springboot.order.entity.Order;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
public class OrderPageResponseDtos {
    private List<Order> orders;
    private PageInfo pageInfo;
}
