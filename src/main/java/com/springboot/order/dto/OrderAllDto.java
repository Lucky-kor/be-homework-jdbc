package com.springboot.order.dto;

import com.springboot.response.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderAllDto<T> {
	private T data;
	private PageInfo pageInfo;
}
