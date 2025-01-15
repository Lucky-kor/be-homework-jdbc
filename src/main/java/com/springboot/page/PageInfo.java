package com.springboot.page;

import lombok.AllArgsConstructor;
import lombok.Getter;

// memberService에서 반환하는 Page 객체(member Page)에서 제공하는
// 페이지 개수, 페이지당 데이터 개수, 총 데이터 수 등의 정보를 이용해
//페이지 정보를 담고 있는 PageInfo 객체를 생성한다.
@Getter
@AllArgsConstructor
public class PageInfo {
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
}
