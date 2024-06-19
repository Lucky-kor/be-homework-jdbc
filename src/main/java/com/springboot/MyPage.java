package com.springboot;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyPage {
    private int pageNumber;
    private int pageSize;

    public MyPage(int pageNumber, int pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }
    public int getStartIndex(){
        return pageSize * (pageNumber - 1);
    }
}
