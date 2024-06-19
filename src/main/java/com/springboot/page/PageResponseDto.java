package com.springboot.page;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Builder
@Setter
public class PageResponseDto {
    private List<?> data;
    private PageInfo pageInfo;
}
