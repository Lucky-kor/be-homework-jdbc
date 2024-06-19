package com.springboot.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@AllArgsConstructor
public class MultiResponseDto<T,F> {
	private T data;
	private PageInfo pageInfo;

	public MultiResponseDto(T data, Page<F> page) {
		this.data = data;
		this.pageInfo = new PageInfo(page.getNumber() + 1, page.getSize(), page.getTotalElements(), page.getTotalPages());
	}
}
