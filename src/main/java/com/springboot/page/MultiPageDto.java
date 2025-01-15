package com.springboot.page;

import com.springboot.member.dto.MemberResponseDto;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class MultiPageDto<T> {
    private List<T> data;
    private PageInfo pageInfo;

    public MultiPageDto(List<T> data, PageInfo pageInfo) {
        this.data = data;
        this.pageInfo = pageInfo;
    }

//    public MultiPageDto(List<T> data, Page page) {
//        this.data = data;
//        this.pageInfo = new PageInfo(page.getNumber() +1 , page.getSize(),
//                page.getTotalElements(), page.getTotalPages());
//    }

}
