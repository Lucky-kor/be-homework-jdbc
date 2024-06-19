package com.springboot.member.dto;

import com.springboot.response.PageInfo;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class MemberAllDto<T,F> {
	private T data;
	private PageInfo pageInfo;

	public MemberAllDto(T data, Page<F> page) {
		this.data = data;
		this.pageInfo = new PageInfo(page.getNumber() + 1, page.getSize(), page.getTotalElements(), page.getTotalPages());
	}
}
