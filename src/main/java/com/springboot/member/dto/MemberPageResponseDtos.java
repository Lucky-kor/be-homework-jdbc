package com.springboot.member.dto;
import com.springboot.order.entity.Order;
import com.springboot.member.entity.Member;
import com.springboot.PageInfo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class MemberPageResponseDtos {
    private List<Member> data;
    private PageInfo pageInfo;

    public MemberPageResponseDtos(List<Member> data, PageInfo pageInfo) {
        this.data = data;
        this.pageInfo = pageInfo;
    }
}
