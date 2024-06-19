package com.springboot.response;

import com.springboot.pagination.PageInfo;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class MultiResponseDto<T> {
    private T datas;
    private PageInfo pageInfo;

    public MultiResponseDto(T datas, Page page) {
        this.datas = datas;
        this.pageInfo = new PageInfo(page.getNumber() + 1, page.getSize(), (int) page.getTotalElements(), page.getTotalPages());
    }
}