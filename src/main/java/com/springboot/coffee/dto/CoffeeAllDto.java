package com.springboot.coffee.dto;

import com.springboot.response.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CoffeeAllDto<T> {
	private T data;
	private PageInfo pageInfo;
}
